# 🧠 N-Queens Solver – Java Implementation

A classic algorithmic problem brought to life in Java!  
This project implements two approaches for solving the **N-Queens Problem**, a constraint satisfaction problem that involves placing `N` queens on an `N×N` chessboard such that no two queens attack each other.

## 📌 What’s Inside

- `solverNQueens.java`:  
  An **iterative repair** solver that attempts to find a valid board configuration using smart **column swaps** and **board rotations**.
  
- `BruteForce.java`:  
  A simple **randomized brute force** solver that generates random board states and checks each until a valid one is found.

- `Board.java`:  
  A fully-featured class to:
  - Represent the board
  - Place queens
  - Swap columns
  - Check for row/column/diagonal attacks
  - Print board state cleanly to console

