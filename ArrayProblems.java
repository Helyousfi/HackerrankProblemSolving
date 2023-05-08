import java.util.*;

class Arrays
{   
    /*
    Problem 01:
    Indices de deux elements dont la somme est target : two sum
    */
    public static int[] TargetIndices(int[] arr, int target){
        if(arr.length == 0) return null;
        // Create a Map that will store (target - arr[i])  
        Map<Integer, Integer> NumbersToFind = new HashMap<>();
        for(int i=0; i < arr.length; i++)
        {
            // Check if NumbersToFind contains target - arr[i], if not add it and continue
            if(NumbersToFind.containsKey(target - arr[i]))
            {
                return new int[] {i, NumbersToFind.get(target - arr[i])};
            }
            NumbersToFind.put(arr[i], i);
        }
        return null;
    }

    /*
    Le plus grand conteneur d'eau
    */
    public static int MaxSumValues(int[] arr){
        int p1 = 0;
        int p2 = arr.length - 1;
        int width, height, maxArea = 0;

        while(p1 < p2){
            height = Math.min(arr[p1], arr[p2]);
            width = p2 - p1;
            maxArea = Math.max(height * width, maxArea);
            if(arr[p1] < arr[p2]){
                p1++;
            }
            else{
                p2--;
            }
        }
        return maxArea;
    }

    
    /*
    arr = {2, 1, 0, 1, 0, 3, 0, 2}
    */
    public static int GetTrappedWater_notOptimized(int[] arr){
        int p, p1, p2;
        int maxLeft = 0, maxRight = 0;

        int totalWater = 0;
        for(p=1; p<arr.length-1; p++){
            p1 = p;
            p2 = p;
            while(p1 >= 0){
                maxLeft = Math.max(arr[p1], arr[p]);
                p1--;
            }
            while(p2 < arr.length){
                maxRight = Math.max(arr[p2], arr[p]);
                p2++;
            }
            totalWater += (Math.min(maxLeft, maxRight) - arr[p]);
            System.out.println(Math.min(maxLeft, maxRight) - arr[p]);
        }
        return totalWater; 
    }

    public static int GetTrappedWater(int[] arr)
    {
        int totalWater = 0;

        int left = 1, right = arr.length - 2;
        int maxLeft = arr[0], maxRight = arr[arr.length - 1];

        while(left <= right)
        {
            if(maxLeft <= maxRight)
            {
                if(arr[left] > maxLeft) {
                    maxLeft = arr[left];
                }
                else {
                    totalWater += maxLeft - arr[left];
                    System.out.println(maxLeft - arr[left]);
                }
                left++;
                System.out.println("left : " + left);
            }
            else{
                if(arr[right] > maxRight) {
                    maxRight = arr[right];
                }
                else {
                    totalWater += maxRight - arr[right];
                    System.out.println(maxRight - arr[right]);
                }
                right--;
                System.out.println("right : " + right);
            }
        }
        return totalWater;
    }


    /*
    "abc#d" is equal to "aba#d" or "abd"
    */
    public static boolean compareStrings(String str1, String str2) {
        
        ArrayDeque<Character> stack1 = new ArrayDeque<>();
        ArrayDeque<Character> stack2 = new ArrayDeque<>();

        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (c == '#') {
                stack1.pop();
            } else {
                stack1.push(c);
            }
        }

        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            if (c == '#') {
                stack2.pop();
            } else {
                stack2.push(c);
            }
        }

        for(char c : stack1)
        {
            System.out.println(c);
        }
        for(char c : stack2)
        {
            System.out.println(c);
        }
        return stack1.equals(stack2);
    }


    public static boolean binarySearch(int[] arr, int target)
    {
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right) / 2;
        while(left < right)
        {
            if(arr[mid] == target) return true;
            else if(arr[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }
        return false;
    }

    public static int lenLastStr(String s) 
    {
        int count = 0;
        for(int i = s.length() - 1; i >= 0; i--)
        {
            if(s.charAt(i) != ' ')
            {
                count++;
            }
            else break;
        }
        return count;
    }
    
    // {1, 2, 6} => 127 {1, 9, 9} => 200
    public static int plusOne(int[] arr)
    {
        int number = 0;
        for(int i = 0; i < arr.length; i++)
        {
            number += arr[i] * Math.pow(10, arr.length - i - 1);
        } 
        return number + 1;
    }

    // Move zeros to last : {0, 1, 0, 3, 12} => {0, 0, 1, 3, 12}
    public static void moveZero(int[] arr)
    {   
        int current = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != 0)
            {
                arr[current] = arr[i];
                current++;
            }
        }
        for(int i = current; i < arr.length; i++)
        {
            arr[i] = 0;
        }
    }

    // Asteroid collision : For each astreoid, abs represents size, 
    // sign represents direction, same speed, state after all collisions
    public static Deque<Integer> collision(int[] asteroids)
    {
        // Create an ArrayDeque that will act like a stack
        Deque<Integer> stack = new ArrayDeque<> ();

        for(int ast : asteroids)
        {
            /* Les cas : 
                - Si ast est positif : 
                    On l'ajoute à la stack
                - Si ast est négatif :
                    3 cas :
                        Tant que peek < abs(ast), 
                            PollLast() 
                        Soit peek < 0:
                            offerLast(ast)
                        Soit peek == abs(ast):
                            pollLast
            */
            if(ast > 0) stack.offerLast(ast);
            else
            {
                while(!stack.isEmpty() && stack.peekLast() > 0 && stack.peekLast() < -ast) stack.pollLast();
                if(stack.isEmpty()) stack.offerLast(ast);
                else if(!stack.isEmpty() && (stack.peekLast() < 0 || stack.peekLast() < -ast)) stack.offerLast(ast);
                else if(!stack.isEmpty() && stack.peekLast() == -ast) stack.pollLast();
            }
        } 
        return stack;
    }


    public static int maxProfitMultiple(int[] candles)
    {
        int maxProfit = 0;
        for(int i = 1; i < candles.length; i++)
        {
            if(candles[i] > candles[i-1]) maxProfit += candles[i] - candles[i-1]; 
        }
        return maxProfit;
    }

    // Find all missing elems {1, 2, 2, 4, 5, 4} => missing elements are 3 and 6 : O(1) space, O(n) time
    public static List<Integer> missingElementsArray(int[] arr)
    {
        List<Integer> result = new ArrayList<> ();
        for(int i=0; i<arr.length; i++){
            if(arr[i] > 0 ) {
                if(arr[arr[i] - 1] > 0) arr[arr[i] - 1] = arr[arr[i] - 1] * -1;
            }
            else{
                if(arr[arr[i] - 1] > 0) arr[-arr[i] - 1] = arr[arr[i] - 1] * -1;
            }
        }
        for(int x:arr){
            if(x>0) result.add(x+1);
        }
        return result;
    }

    // Given an array {-3, 4, 6, -2, 5, 6, -5} return max sum of sub arrays {4, 6, -2, 5, 6}
    public static int maxSubArray(int[] arr)
    {
        int max_sum = arr[0];
        int current = arr[0];
        for(int x : arr)
        {
            current = Math.max(current + x, x);
            max_sum = Math.max(max_sum, current); 
        }
        return max_sum;
    }


    // Merge two arrays in contsant space complexity : think about iterating from last element
    // Prouct except : product of all elements then divide by each element


    // Group anagrams
    /* public static Map<String, List<String>> getAnagrams(String[] arr) {
        Map<String, List<String>> map = new HashMap<>();
        for (String x : arr) {
            char[] chars = x.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(x);
            map.put(sorted, list);
        }
        return map;
    } */


    // {1, 3, 1, 1, 4} return true : two pointers at the end
    public static boolean jump(int[] arr)
    {
        int pos = arr.length - 1;
        int prev = pos - 1;
        for(int i = arr.length-1; i >= 0; i--)
        {
            if(pos - prev <= arr[i] && pos != 0)
            {
                pos = prev;9
                prev = pos - 1;
            }
            else
            {
                prev--;
            }
        }
        if(pos == 0) return true;
        return false;
    }



    public static void main(String args[])
    {
        System.out.println(jump(new int[] {1, 2, 1, 0, 4} ));
    }
}
