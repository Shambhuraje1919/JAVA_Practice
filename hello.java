import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null) return res;
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        // Precompute palindrome table
        for (int center = 0; center < n; center++) {
            // odd length
            int l = center, r = center;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                pal[l][r] = true;
                l--; r++;
            }
            // even length
            l = center; r = center + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                pal[l][r] = true;
                l--; r++;
            }
        }

        dfs(0, s, new ArrayList<>(), res, pal);
        return res;
    }

    private void dfs(int index, String s, List<String> path, List<List<String>> res, boolean[][] pal) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (pal[index][i]) {
                path.add(s.substring(index, i + 1));
                dfs(i + 1, s, path, res, pal);
                path.remove(path.size() - 1);
            }
        }
    }

    // quick test
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aab";
        List<List<String>> result = solution.partition(s);
        for (List<String> partition : result) {
            System.out.println(partition);
        }
    }
}
