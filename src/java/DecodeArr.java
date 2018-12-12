import java.util.*;

public class DecodeArr {
    // k1=v1;k2=v2\nA=XXX
    public static String store(List<Map<String, String>> arr) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> vals : arr) {
            if (vals != null && !vals.isEmpty()) {
                for (Map.Entry<String, String> keyVal : vals.entrySet()) {
                    sb.append(keyVal.getKey()).append("=").append(keyVal.getValue()).append(";");
                }
                sb.deleteCharAt(sb.length() - 1); // remove last ;
                sb.append("\\\\n");
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 3, sb.length());
        }
        return sb.toString();
    }

    public static List<Map<String, String>> load(String a) {
        if (a == null || "".equals(a.trim())) {
            return Collections.emptyList();
        }
        List<Map<String, String>> arr = new ArrayList<>();
        String[] mapKeyVals = a.split("\\\\n");
        if (mapKeyVals.length > 0) {
            for (String keyVals : mapKeyVals) {
                if (keyVals != null && !"".equals(keyVals.trim())) {
                    String[] keyValArr = keyVals.split(";");
                    if (keyValArr.length > 0) {
                        Map<String, String> keyValMap = new HashMap<>(keyValArr.length);
                        for (String keyVal : keyValArr) {
                            if (keyVal != null && keyVal.contains("=")) {
                                String[] kv = keyVal.split("=");
                                if (kv.length == 2) {
                                    // normal
                                    keyValMap.put(kv[0], kv[1]);
                                } else if (kv.length == 1 && keyVal.indexOf("=") > 0){
                                    // k=null
                                    keyValMap.put(kv[0], "");
                                } else if (kv.length == 1 && keyVal.indexOf("=") == 0) {
                                    // null = v
                                    if (keyValMap.containsKey("")) {
                                        throw new RuntimeException("string format wrong, can't support two empty key in one line");
                                    }
                                    keyValMap.put("", kv[0]);
                                }
                            }
                        }
                        arr.add(keyValMap);
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        String a = "k1=v1;k2=v2\\nA=XXX";
        List<Map<String, String>> res = load(a);
        res.stream().forEach(m -> m.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue())));

        String t = store(res);
        System.out.println("store as: " + t);

        // try edge case
        String a1 = "k1=v1;k2=v2\\nA=XXX\\n=22;t=33";
        List<Map<String, String>> res1 = load(a1);
        res1.stream().forEach(m -> m.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue())));

        String t1 = store(res1);
        System.out.println("store as: " + t1);
    }


}
