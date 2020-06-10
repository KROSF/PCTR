#include <mutex>
#include <condition_variable>
#include <vector>
#include <thread>
#include <iostream>

struct Semaphore
{
  std::mutex mu;
  int permits;
  std::condition_variable queue;

  Semaphore(int s = 1) : permits(s) {}

  auto waitS() -> void
  {
    std::unique_lock<std::mutex> lock(mu);
    while (permits == 0)
    {
      queue.wait(lock);
    }

    --permits;
  }

  auto signalS() -> void
  {
    std::unique_lock<std::mutex> lock(mu);
    ++permits;
    queue.notify_all();
  }
};

auto func(unsigned long long &n, Semaphore &S) -> void
{
  for (size_t i = 0; i < 10000000; i++)
  {
    S.waitS();
    n++;
    S.signalS();
  }
}

auto main() -> int
{
  unsigned long long n = 0;
  Semaphore S(1);

  std::vector<std::thread> threads(8);

  for (auto &thread : threads)
  {
    thread = std::thread(func, std::ref(n), std::ref(S));
  }

  for (auto &thread : threads)
  {
    thread.join();
  }

  std::cout << n << std::endl;
}