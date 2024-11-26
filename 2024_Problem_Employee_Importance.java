//690. Employee Importance - https://leetcode.com/problems/employee-importance/
//Time Complexity: O(n)
//Space Complexity: O(n)

//BFS
class Solution {
    HashMap<Integer, Employee> map;
    public int getImportance(List<Employee> employees, int id) {
        this.map = new HashMap<>();
        //save emp details in map with key as id
        for(Employee emp : employees){
            map.put(emp.id, emp);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(id);
        int result = 0;
        while(!q.isEmpty()){
            int currId = q.poll();
            Employee currEmp = map.get(currId);
            //get total importance
            result = result + currEmp.importance;
            //iterate over subordinate list and add each in queue
            for(int subIds : currEmp.subordinates){
                q.add(subIds);
            }
        }
        return result;
    }
}

//DFS
class Solution {
    HashMap<Integer, Employee> map;
    int result;
    public int getImportance(List<Employee> employees, int id) {
        this.map = new HashMap<>();
        this.result = 0;
        //save emp details in map with key as id
        for(Employee emp : employees){
            map.put(emp.id, emp);
        }
        dfs(id);
        return result;
    }
    private void dfs(int id){
        Employee currEmp = map.get(id);
        //get total importance
        result = result + currEmp.importance;
        //iterate over subordinate list and add each in queue
        for(int subIds : currEmp.subordinates){
            dfs(subIds);
        }
    }
}