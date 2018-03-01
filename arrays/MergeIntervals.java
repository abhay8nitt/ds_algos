package arrays;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * For example,
 * Given: [1,3],[2,6],[8,10],[15,18],
 * Output: [1,6],[8,10],[15,18].
 */
public class MergeIntervals {

    MergeIntervals() {
    }

    public java.util.List<Interval> mergeIntervals(java.util.List<Interval> intervals) {
        System.out.println("Intervals:" + intervals.toString());
        Collections.sort(intervals);
        java.util.List<Interval> result = new ArrayList<>();
        int size = intervals.size();
        if (size == 0) return result;
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (int i = 1; i < size; i++) {
            Interval temp = intervals.get(i);
            if (end >= intervals.get(i).start) {
                end = Math.max(end, temp.end);
            } else {
                Interval tobeAdded = new Interval(start, end);
                result.add(tobeAdded);
                start = intervals.get(i).start;
                end = intervals.get(i).end;
            }
        }
        result.add(new Interval(start, end));
        return result;
    }
}

class Interval implements Comparable {
    int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Object o) {
        Interval other = (Interval) o;
        if (start > other.start) return 1;
        if (start == other.start) return 0;
        return -1;
    }

    @Override
    public String toString() {
        return "_" +
                start +
                "," + end +
                "_";
    }
}
