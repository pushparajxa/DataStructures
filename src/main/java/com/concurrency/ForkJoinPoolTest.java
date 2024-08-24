/*
 ** COPYRIGHT **
 */
package com.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// Reference: 1. https://www.baeldung.com/java-fork-join
//            2. https://www.baeldung.com/java-work-stealing
public class ForkJoinPoolTest {
    
    HashMap<String, HashMap<String, Long>> users = new HashMap<>();
    
    public static void main(String[] args) {
        ForkJoinPoolTest test = new ForkJoinPoolTest();
        test.initializeTheTestData();
        GetFriendsTask getFriendsTask = new GetFriendsTask("User1", 2, test.users);
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        Set<String> friends = commonPool.invoke(getFriendsTask);
        System.out.println(friends);
        
        commonPool.shutdownNow();
        
        
    }
    //Get friends of friends of a given user, i,e, level 2.
    
    public void initializeTheTestData(){
        // Current timestamp for simplicity, you can replace with actual timestamps
        long currentTime = System.currentTimeMillis();
        
        // User 1: Friends with everyone
        HashMap<String, Long> user1Friends = new HashMap<>();
        user1Friends.put("User2", currentTime);
        user1Friends.put("User3", currentTime);
        user1Friends.put("User4", currentTime);
        user1Friends.put("User5", currentTime);
        users.put("User1", user1Friends);
        
        // User 2: Friends with User1 and User3
        HashMap<String, Long> user2Friends = new HashMap<>();
        user2Friends.put("User1", currentTime);
        user2Friends.put("User3", currentTime);
        users.put("User2", user2Friends);
        
        // User 3: Friends with User1, User2, and User4
        HashMap<String, Long> user3Friends = new HashMap<>();
        user3Friends.put("User1", currentTime);
        user3Friends.put("User2", currentTime);
        user3Friends.put("User4", currentTime);
        users.put("User3", user3Friends);
        
        // User 4: Only friends with User1
        HashMap<String, Long> user4Friends = new HashMap<>();
        user4Friends.put("User1", currentTime);
        users.put("User4", user4Friends);
        
        // User 5: No friends
        users.put("User5", new HashMap<>());
        
        ExecutorService executorService = Executors.newScheduledThreadPool(2);
        Future<String> hello = executorService.submit(() -> {
            System.out.println("hello");
            return "Hello";
        });
    }
    
    static  class GetFriendsTask extends RecursiveTask<Set<String>>{
        
        String user;
        int level;
        HashMap<String, HashMap<String, Long>> users;
        
        GetFriendsTask(String user, int level, HashMap<String, HashMap<String, Long>> users ){
            this.user = user;
            this.level = level;
            this.users = users;
            
        }
        
        @Override
        protected Set<String> compute() {
            
            if(level == 1 ){
                Set<String> list = users.get(user).keySet().stream().collect(Collectors.toSet());
                System.out.println("Level="+level+". User="+ user+". Friends="+ list);
                return list;
            }
            else {
                Stream<GetFriendsTask> friendsTaskStream = ForkJoinTask.invokeAll(
                    users.get(user).keySet().stream().map(friend -> new GetFriendsTask(friend,
                        level - 1, users)).collect(Collectors.toSet())).stream();
                Stream<String> listStream =
                    friendsTaskStream.flatMap(task ->  task.join().stream());
                Set<String> friendsOfFriends = listStream.collect(Collectors.toSet());
                
                Set<String> thisUsersImmediateFriends = users.get(user).keySet().stream().collect(Collectors.toSet());
                
                friendsOfFriends.addAll(thisUsersImmediateFriends);
                
                return friendsOfFriends;
                
            }
            
        }
    }
    
    
    
    
    
}

 class CustomRecursiveTask extends RecursiveTask<Integer> {
    private int[] arr;
    
    private static final int THRESHOLD = 20;
    
    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }
    
    @Override
    protected Integer compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                .stream()
                .mapToInt(x->x.join())
                .sum();
        } else {
            return processing(arr);
        }
    }
    
    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
            Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
            Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }
    
    private Integer processing(int[] arr) {
        return Arrays.stream(arr)
            .filter(a -> a > 10 && a < 27)
            .map(a -> a * 10)
            .sum();
    }
}