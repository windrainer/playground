package com.sen.playground.algorithm;

import java.util.LinkedList;

public class BFSCountIslandBFS {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
                {'0', '0', '0', '1', '1'}

        };

        System.out.println(numberOfIlands(grid));
    }

    public static int numberOfIlands(char[][] grid) {
        if(0 >= grid.length) {
            return 0;
        }

        int rowSize = grid.length;
        int colSize = grid[0].length;

        boolean[][] visited = new boolean[rowSize][colSize];
        initVisitedArray(visited);

        int count = 0;
        for(int i=0; i<rowSize; i++) {
            for(int j=0; j<colSize; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isSafe(char[][] grid, boolean[][] visited, int i, int j) {
        int rowSize = grid.length;
        int colSize = grid[0].length;
        return (i >= 0 && i < rowSize) && (j>=0 && j < colSize) && (!visited[i][j] && grid[i][j] == '1');
    }


    //This only followed a bfs way to traverse the matrix
    private static void bfs(char[][] grid, boolean[][] visited, int i, int j) {
        int[] rowIndex = { -1, 0 , 0,  1 };
        int[] colIndex = { 0, -1, 1,  0  };

        LinkedList<Index> queue = new LinkedList<>();
        Index index = new Index(i, j);
        queue.offer(index);
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            Index node = queue.poll();
            System.out.println(String.format("row=%s, col=%s", node.getRow(), node.getCol()));
            for(int k=0; k<4; k++) {
                Index nextNode = new Index(node.getRow() + rowIndex[k],node.getCol() + colIndex[k]);
                if(isSafe(grid, visited,node.getRow() + rowIndex[k] ,node.getCol() + colIndex[k])) {
                    visited[node.getRow() + rowIndex[k]][node.getCol() + colIndex[k]] = true;
                    queue.offer(nextNode);
                }
            }
        }
        System.out.println("Found 1 island");
    }

    private static void initVisitedArray(boolean[][] visited) {
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[i].length; j++) {
                visited[i][j] = false;
            }
        }
    }


    static class Index {
        private int row;
        private int col;

        public Index() {}
        public Index(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }
}
