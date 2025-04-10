# LRU Cache in Java

This project implements a **Least Recently Used (LRU) Cache** in Java. The cache provides efficient O(1) time complexity for `put` and `get` operations by using a combination of a **hash map** and a **doubly linked list**. It also ensures **thread-safety** for concurrent access.

## Features

- **Fixed capacity**: Set during initialization.
- **O(1) operations**: Efficient `put()` and `get()` methods.
- **Automatic eviction**: Removes least recently used item when capacity is exceeded.
- **Thread-safe**: Safe for concurrent use via internal locking.

## Operations

- `put(key, value)`: Inserts a key-value pair into the cache.
    - If the key already exists, its value is updated and moved to the front (most recently used).
    - If the key is new and the cache is full, the least recently used item is removed first.

- `get(key)`: Retrieves the value for the given key.
    - If the key exists, it is moved to the front of the cache and its value is returned.
    - If the key does not exist, `null` is returned.