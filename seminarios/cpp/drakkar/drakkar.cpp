#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>
#include <vector>

using std::condition_variable;
using std::cout;
using std::endl;
using std::mutex;
using std::recursive_mutex;
using std::thread;
using std::unique_lock;

struct Marmita {
  Marmita(int inicial = 10) { anguilas = inicial; }
  void comer() {
    unique_lock<mutex> ul_comer(lock_comer);
    cv_cocinero.notify_one();
    cv_comer.wait(ul_comer, [this]() { return !(anguilas == 0); });
    anguilas--;
    cout << "Comiendo " << anguilas << endl;
  }
  void cocinar() {
    unique_lock<mutex> ul_cocina(lock_cocinero);
    cv_cocinero.wait(ul_cocina, [this]() { return (anguilas > 0); });
    anguilas = 10;
    cout << "Cocinando " << anguilas << endl;
    cv_comer.notify_all();
  }
  mutex lock_cocinero;
  mutex lock_comer;
  condition_variable cv_cocinero;
  condition_variable cv_comer;
  int anguilas;
};

void vikingo(bool type, Marmita& m) {
  if (type == true) {
    m.cocinar();
    // cout << "vikingo proveedor" << endl;
  } else if (type == false) {
    m.comer();
    // cout << "vikingo gloton" << endl;
  }
}

int main(int argc, char const* argv[]) {
  Marmita m;
  thread d(vikingo, false, std::ref(m));
  thread b(vikingo, false, std::ref(m));
  thread c(vikingo, true, std::ref(m));
  thread a(vikingo, false, std::ref(m));
  a.join();
  b.join();
  c.join();
  d.join();
  return 0;
}
