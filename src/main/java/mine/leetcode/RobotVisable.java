package mine.leetcode;

import java.util.LinkedList;

public class RobotVisable {
    private boolean[][] vis;

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int indexSum(int x) {
        int s = 0;
        while(x!=0){
            s+=(x%10);
            x/=10;
        }
        return s;
    }

    private int indexSum(Point point) {
        return indexSum(point.x) + indexSum(point.y);
    }

    private boolean isVis(Point point) {
        return vis[point.x][point.y];
    }

    private void setVis(Point point) {
        vis[point.x][point.y] = true;
    }

    public int movingCount(int m, int n, int k) {
        if (k == 0)
            return 1;
        vis = new boolean[m][n];
        LinkedList<Point> q = new LinkedList<>();
        q.addLast(new Point(0, 0));
        vis[0][0] = true;
        int ans = 1;
        while (!q.isEmpty()) {
            Point p = q.pollFirst();
            Point p1 = new Point(p.x + 1, p.y);
            if (p1.x < m && !isVis(p1) && indexSum(p1) <= k) {
                q.addLast(p1);
                ans++;
                setVis(p1);
            }
            Point p2 = new Point(p.x, p.y + 1);
            if (p2.y < n && !isVis(p2) && indexSum(p2) <= k) {
                q.addLast(p2);
                ans++;
                setVis(p2);
            }
        }
        return ans;
    }
}