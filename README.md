##Question 1. Data Store and Load  
A program uses an array a of map(dictionary). The keys and values of each map(dictionary) are both strings. In different languages, the corresponding type of map is: PHP array, Java HashMap, C++ std::map, Objective-C NSDictionary, Swift Dictionary, Python dict, JavaScript object, etc.   
For example:  
a[0]["key1"]="value1" a[0]["key2"]="value2" a[1]["keyA"]="valueA" ...   
In order to store and load array 𝑎, we use a string-based format where each line corresponds to each element of array a:
text="key1=value1;key2=value2\nkeyA=valueA\n..."  
Please implement a store function and a load function.  
text=store(a); //store 𝑎 in a string-type variable 𝑡𝑒𝑥𝑡 a=load(text); //load array 𝑎 from variable text   
You must follow the format:   
key/value pairs are separated by '=' and ';' key/value may contain any character.   
items are separated by '\n'.   
 
##Question 2. Finding Optimal Path  
Assume there is a directed acyclic graph, where every vertex has a positive weight. We need to find a path such that the sum of the weights of all vertices on the path is maximized.   
Input: n vertices, m edges, origin vertex Output: sum of the optimal path   
For example, 3 vertices with weights: A1   
B2   
C2   
and 3 edges:   
A->B   
B->C   
A->C   
With origin vertex A, the output is 5, where optimal path is A → B → C with total weight 1 + 2 + 2 = 5.   
Note: The assumption here is directed acyclic graph. What if the graph may not be acyclic, how to avoid infinite loop caused by cycles?   