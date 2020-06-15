#include <thread>
#include <mutex>
#include <condition_variable>
#include <iostream>
#include <vector>

struct Monitor
{
  Monitor() : airplanes(0)
  {
  }

  void access()
  {
    std::unique_lock<std::mutex> lock(me);

    while (airplanes == 50)
    {
      queue.wait(lock);
    }
    ++airplanes;
  }

  void leave()
  {
    std::unique_lock<std::mutex> lock(me);
    --airplanes;
    queue.notify_all();
  }

private:
  std::mutex me;
  int airplanes;
  std::condition_variable queue;
};

void airplanes(int id, Monitor &m)
{
  m.access();
  std::cout << "La aeronave " << id << "entra en el espacio aereo de barajas" << std::endl;
  std::this_thread::sleep_for(std::chrono::milliseconds(1000));
  std::cout << "La aeronave " << id << " deja el espacio aereo de barajas" << std::endl;
  m.leave();
}

int main()
{
  std::vector<std::thread> threads(100);
  Monitor m;

  for (size_t i = 0; i < threads.size(); ++i)
  {
    threads[i] = std::thread(airplanes, i, std::ref(m));
  }

  for (auto &thread : threads)
  {
    thread.join();
  }

  return 0;
}