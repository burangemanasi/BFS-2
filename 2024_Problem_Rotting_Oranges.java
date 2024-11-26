//994. Rotting Oranges - https://leetcode.com/problems/rotting-oranges/description/
//Time Complexity: O(m*n)
//Space Complexity: O(m*n)

//BFS
class Solution {
    int[][] dirs;
    int m,n;
    public int orangesRotting(int[][] grid) {
        this.dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        this.m = grid.length;
        this.n = grid[0].length;
        int fresh = 0;
        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        //go through the matrix to find rotten oranges and get total fresh count
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                //add rotten oranges to the queue
                if(grid[i][j] == 2){
                    q.add(new int[]{i,j}); //add all rotten oranges to the queue
                } else if(grid[i][j] == 1){
                    fresh++; //count of fresh oranges
                }
            }
        }
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] curr = q.poll();
                for(int[] dir : dirs){ //traverse through all the dirs
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    //check the bounds
                    if(r>=0 && c>=0 && r<m && c<n && grid[r][c] == 1){
                        grid[r][c]=2; //mark rotten
                        q.add(new int[]{r,c}); //add rotten orange to the queue
                        fresh--; //decrement as fresh -> rotten
                    }
                }
            }
            time++;
        }
        if(fresh != 0) return -1;
        return time-1;
    }
}


//DFS
//Time Complexity: O(m^2 * n^2)
//Space Complexity: O(m*n)
class Solution {
    int[][] dirs;
    int m,n;
    public int orangesRotting(int[][] grid) {
        this.dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        this.m = grid.length;
        this.n = grid[0].length;
        int time = 2;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 2){
                    dfs(grid, i, j, time);
                }
            }
        }
        int result = 2;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1){
                    return -1;
                }
                result = Math.max(result, grid[i][j]);
            }
        }
        return result - time;
    }

    private void dfs(int[][] grid, int i, int j, int time){
        //base case
        if(i<0 || j<0 || i==m || j==n || grid[i][j]==0) return;
        if(grid[i][j] != 1 && grid[i][j] < time) return;
        //logic
        grid[i][j] = time;
        for(int[] dir : dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;

            dfs(grid, r, c, time+1);
        }
    }
}