
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Node {
    String name;
    int val;

    public Node() {
    }

    public Node(int val, String name) {
        this.name = name;
        this.val = val;
    }
}

class Path {
    Node start;
    Node end;

    public Path() {

    }

    public Path(Node start, Node end) {
        this.start = start;
        this.end = end;
    }
}


public class MaxDAGNodeSum {
    public static int MaxPathSum(List<Path> paths, List<Node> nodes, Node start) {
        Map<String, Node> nodeVals = nodes.stream().collect(Collectors.toMap(x->x.name, x->x));
        return findNextPathSum(paths, nodeVals, start, null, start.val);
    }

    // DFS
    private static int findNextPathSum(List<Path> paths, Map<String, Node> nodes, Node start, Node before, int sum) {
        List<Path> validPaths = paths.stream().filter(p -> p.start.equals(start) && !p.end.equals(start) && !p.end.equals(before)).collect(Collectors.toList());
        if (validPaths.size() == 0) {
            return sum;
        }
        // ABC-> 5,  AC-> 3
        int max = sum;
        for (Path p : validPaths) {
            int val = findNextPathSum(paths, nodes, nodes.get(p.end.name), start, sum) + nodes.get(p.end.name).val;
            max = max > val ? max : val;
        }
        return max;
    }

    public static void main(String[] args) {
        Node A = new Node(1, "A");
        Node B = new Node(2, "B");
        Node C = new Node(2, "C");
        Path AB = new Path(A, B);
        Path AC = new Path(A, C);
        Path BC = new Path(B, C);

        System.out.println(MaxPathSum(Arrays.asList(AB, AC, BC), Arrays.asList(A, B, C), A));

        Node D = new Node(4, "D");
        Node E = new Node(1, "E");
        Path AD = new Path(A, D);
        Path DE = new Path(D, E);

        System.out.println(MaxPathSum(Arrays.asList(AB, AC, BC, AD, DE), Arrays.asList(A, B, C, D, E), A)); // 6
    }

}
