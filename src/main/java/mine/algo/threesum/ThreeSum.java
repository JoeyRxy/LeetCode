package mine.algo.threesum;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// import edu.princeton.cs.algs4.Out;

/**
 * ThreeSum
 * <p>
 * 答案（三元组）无重复……怎么做到？
 */
@SuppressWarnings("unused")
public class ThreeSum {
    // private int[] a;
    // private List<List<Integer>> ans = new ArrayList<>();
    // private int len;

    // public List<List<Integer>> threeSum(int[] nums) {
    // if (nums.length < 3)
    // return ans;
    // this.a = nums;
    // len = nums.length;
    // binSearch();
    // return validate();
    // }

    /**
     * SortAns
     */
    public class SortAns implements Comparator<List<Integer>> {

        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            if (o1.get(0) > o2.get(0))
                return 1;
            else if (o1.get(0) == o2.get(0)) {
                if (o1.get(1) > o2.get(1))
                    return 1;
                else if (o1.get(1) == o2.get(1))
                    return 0;
                else
                    return -1;
            } else
                return -1;
        }
    }

    public List<List<Integer>> validate(List<List<Integer>> numsList) {
        // Collections.sort(ans, new SortAns()); // "message": "The method sort(T[],
        // Comparator<? super T>) in thetype
        // Arrays is
        // not applicable for the arguments (List<List<Integer>>,ThreeSum.SortAns)"
        // ATTENTION : Use "Collections.sort" instead!
        if (numsList.size() == 0)
            return numsList;
        numsList.sort(new SortAns());
        int size = numsList.size();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = numsList.get(0), cur;
        res.add(tmp);
        for (int i = 1; i != size; i++) {
            cur = numsList.get(i);
            if (!cur.equals(tmp)) {
                tmp = cur;
                res.add(cur);
            }
        }
        return res;
    }

    // ============== NO.1:brute ==============//
    // private void brute() {
    // List<Integer> tmp;
    // for (int i = 0; i != len - 2; i++) {
    // for (int j = i + 1; j != len - 1; j++) {
    // for (int k = j + 1; k != len; k++) {
    // if (a[i] + a[j] + a[k] == 0) {
    // tmp = new ArrayList<>();
    // tmp.add(a[i]);
    // tmp.add(a[j]);
    // tmp.add(a[k]);
    // ans.add(tmp);
    // }
    // }
    // }
    // }
    // }

    // ============== NO.2:binary search ==============//
    private List<List<Integer>> binSearch(int[] a) {
        Arrays.sort(a);
        int tmp;
        int len = a.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i != len - 2; i++) {
            if (a[i] > 0)
                break;
            for (int j = i + 1; j != len - 1; j++) {
                tmp = a[i] + a[j];
                if (tmp > 0)
                    break;
                // if (TMP.size() != 0) {
                // if (a[i] == TMP.get(0) && a[j] == TMP.get(1))
                // continue;
                // }
                if (Arrays.binarySearch(a, j + 1, len, -tmp) > 0) {
                    ans.add(Arrays.asList(a[i], a[j], -tmp));
                }
            }
        }
        return validate(ans);
    }

    // TODO ： 还有O(n^2)的方法？？
    // public List<List<Integer>> theAnswer(int[] nums) {
    // Arrays.sort(nums);
    // List<List<Integer>> res = new LinkedList<>();
    // for (int i = 0; i < nums.length - 2; i++) {
    // if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
    // int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
    // while (lo < hi) {
    // if (nums[lo] + nums[hi] == sum) {
    // res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
    // while (lo < hi && nums[lo] == nums[lo + 1])
    // lo++;
    // while (lo < hi && nums[hi] == nums[hi - 1])
    // hi--;
    // lo++;
    // hi--;
    // } else if (nums[lo] + nums[hi] < sum)
    // lo++;
    // else
    // hi--;
    // }
    // }
    // }
    // return res;
    // }

    // public static int sum(List<Integer> a) {
    // int sum = 0;
    // for (int x : a) {
    // sum += x;
    // }
    // return sum;
    // }

    // public static void main(String[] args) {
    // int[] nums = { 82597, -9243, 62390, 83030, -97960, -26521, -61011, 83390,
    // -38677, 12333, 75987, 46091, 83794,
    // 19355, -71037, -6242, -28801, 324, 1202, -90885, -2989, -95597, -34333,
    // 35528, 89870, 75860, -70802,
    // 21779, 14184, -16511, -89156, -31422, 71470, 69600, -78498, 74079, -19410,
    // 40311, 28501, 26397, -67574,
    // -32518, 68510, 38615, 19355, -6088, -97159, -29255, -92523, 3023, -42536,
    // -88681, 64255, 41206, 44119,
    // 52208, 39522, -52108, 91276, -70514, 83436, 63289, -79741, 9623, 99559,
    // 12642, 85950, 83735, -21156,
    // -67208, 98088, -7341, -27763, -30048, -44099, -14866, -45504, -91704, 19369,
    // 13700, 10481, -49344,
    // -85686, 33994, 19672, 36028, 60842, 66564, -24919, 33950, -93616, -47430,
    // -35391, -28279, 56806, 74690,
    // 39284, -96683, -7642, -75232, 37657, -14531, -86870, -9274, -26173, 98640,
    // 88652, 64257, 46457, 37814,
    // -19370, 9337, -22556, -41525, 39105, -28719, 51611, -93252, 98044, -90996,
    // 21710, -47605, -64259,
    // -32727, 53611, -31918, -3555, 33316, -66472, 21274, -37731, -2919, 15016,
    // 48779, -88868, 1897, 41728,
    // 46344, -89667, 37848, 68092, -44011, 85354, -43776, 38739, -31423, -66330,
    // 65167, -22016, 59405, 34328,
    // -60042, 87660, -67698, -59174, -1408, -46809, -43485, -88807, -60489, 13974,
    // 22319, 55836, -62995,
    // -37375, -4185, 32687, -36551, -75237, 58280, 26942, -73756, 71756, 78775,
    // -40573, 14367, -71622, -77338,
    // 24112, 23414, -7679, -51721, 87492, 85066, -21612, 57045, 10673, -96836,
    // 52461, -62218, -9310, 65862,
    // -22748, 89906, -96987, -98698, 26956, -43428, 46141, 47456, 28095, 55952,
    // 67323, -36455, -60202, -43302,
    // -82932, 42020, 77036, 10142, 60406, 70331, 63836, 58850, -66752, 52109,
    // 21395, -10238, -98647, -41962,
    // 27778, 69060, 98535, -28680, -52263, -56679, 66103, -42426, 27203, 80021,
    // 10153, 58678, 36398, 63112,
    // 34911, 20515, 62082, -15659, -40785, 27054, 43767, -20289, 65838, -6954,
    // -60228, -72226, 52236, -35464,
    // 25209, -15462, -79617, -41668, -84083, 62404, -69062, 18913, 46545, 20757,
    // 13805, 24717, -18461, -47009,
    // -25779, 68834, 64824, 34473, 39576, 31570, 14861, -15114, -41233, 95509,
    // 68232, 67846, 84902, -83060,
    // 17642, -18422, 73688, 77671, -26930, 64484, -99637, 73875, 6428, 21034,
    // -73471, 19664, -68031, 15922,
    // -27028, 48137, 54955, -82793, -41144, -10218, -24921, -28299, -2288, 68518,
    // -54452, 15686, -41814,
    // 66165, -72207, -61986, 80020, 50544, -99500, 16244, 78998, 40989, 14525,
    // -56061, -24692, -94790, 21111,
    // 37296, -90794, 72100, 70550, -31757, 17708, -74290, 61910, 78039, -78629,
    // -25033, 73172, -91953, 10052,
    // 64502, 99585, -1741, 90324, -73723, 68942, 28149, 30218, 24422, 16659, 10710,
    // -62594, 94249, 96588,
    // 46192, 34251, 73500, -65995, -81168, 41412, -98724, -63710, -54696, -52407,
    // 19746, 45869, 27821, -94866,
    // -76705, -13417, -61995, -71560, 43450, 67384, -8838, -80293, -28937, 23330,
    // -89694, -40586, 46918,
    // 80429, -5475, 78013, 25309, -34162, 37236, -77577, 86744, 26281, -29033,
    // -91813, 35347, 13033, -13631,
    // -24459, 3325, -71078, -75359, 81311, 19700, 47678, -74680, -84113, 45192,
    // 35502, 37675, 19553, 76522,
    // -51098, -18211, 89717, 4508, -82946, 27749, 85995, 89912, -53678, -64727,
    // -14778, 32075, -63412, -40524,
    // 86440, -2707, -36821, 63850, -30883, 67294, -99468, -23708, 34932, 34386,
    // 98899, 29239, -23385, 5897,
    // 54882, 98660, 49098, 70275, 17718, 88533, 52161, 63340, 50061, -89457, 19491,
    // -99156, 24873, -17008,
    // 64610, -55543, 50495, 17056, -10400, -56678, -29073, -42960, -76418, 98562,
    // -88104, -96255, 10159,
    // -90724, 54011, 12052, 45871, -90933, -69420, 67039, 37202, 78051, -52197,
    // -40278, -58425, 65414, -23394,
    // -1415, 6912, -53447, 7352, 17307, -78147, 63727, 98905, 55412, -57658,
    // -32884, -44878, 22755, 39730,
    // 3638, 35111, 39777, 74193, 38736, -11829, -61188, -92757, 55946, -71232,
    // -63032, -83947, 39147, -96684,
    // -99233, 25131, -32197, 24406, -55428, -61941, 25874, -69453, 64483, -19644,
    // -68441, 12783, 87338,
    // -48676, 66451, -447, -61590, 50932, -11270, 29035, 65698, -63544, 10029,
    // 80499, -9461, 86368, 91365,
    // -81810, -71914, -52056, -13782, 44240, -30093, -2437, 24007, 67581, -17365,
    // -69164, -8420, -69289,
    // -29370, 48010, 90439, 13141, 69243, 50668, 39328, 61731, 78266, -81313,
    // 17921, -38196, 55261, 9948,
    // -24970, 75712, -72106, 28696, 7461, 31621, 61047, 51476, 56512, 11839,
    // -96916, -82739, 28924, -99927,
    // 58449, 37280, 69357, 11219, -32119, -62050, -48745, -83486, -52376, 42668,
    // 82659, 68882, 38773, 46269,
    // -96005, 97630, 25009, -2951, -67811, 99801, 81587, -79793, -18547, -83086,
    // 69512, 33127, -92145, -88497,
    // 47703, 59527, 1909, 88785, -88882, 69188, -46131, -5589, -15086, 36255,
    // -53238, -33009, 82664, 53901,
    // 35939, -42946, -25571, 33298, 69291, 53199, 74746, -40127, -39050, 91033,
    // 51717, -98048, 87240, 36172,
    // 65453, -94425, -63694, -30027, 59004, 88660, 3649, -20267, -52565, -67321,
    // 34037, 4320, 91515, -56753,
    // 60115, 27134, 68617, -61395, -26503, -98929, -8849, -63318, 10709, -16151,
    // 61905, -95785, 5262, 23670,
    // -25277, 90206, -19391, 45735, 37208, -31992, -92450, 18516, -90452, -58870,
    // -58602, 93383, 14333, 17994,
    // 82411, -54126, -32576, 35440, -60526, -78764, -25069, -9022, -394, 92186,
    // -38057, 55328, -61569, 67780,
    // 77169, 19546, -92664, -94948, 44484, -13439, 83529, 27518, -48333, 72998,
    // 38342, -90553, -98578, -76906,
    // 81515, -16464, 78439, 92529, 35225, -39968, -10130, -7845, -32245, -74955,
    // -74996, 67731, -13897,
    // -82493, 33407, 93619, 59560, -24404, -57553, 19486, -45341, 34098, -24978,
    // -33612, 79058, 71847, 76713,
    // -95422, 6421, -96075, -59130, -28976, -16922, -62203, 69970, 68331, 21874,
    // 40551, 89650, 51908, 58181,
    // 66480, -68177, 34323, -3046, -49656, -59758, 43564, -10960, -30796, 15473,
    // -20216, 46085, -85355, 41515,
    // -30669, -87498, 57711, 56067, 63199, -83805, 62042, 91213, -14606, 4394,
    // -562, 74913, 10406, 96810,
    // -61595, 32564, 31640, -9732, 42058, 98052, -7908, -72330, 1558, -80301,
    // 34878, 32900, 3939, -8824,
    // 88316, 20937, 21566, -3218, -66080, -31620, 86859, 54289, 90476, -42889,
    // -15016, -18838, 75456, 30159,
    // -67101, 42328, -92703, 85850, -5475, 23470, -80806, 68206, 17764, 88235,
    // 46421, -41578, 74005, -81142,
    // 80545, 20868, -1560, 64017, 83784, 68863, -97516, -13016, -72223, 79630,
    // -55692, 82255, 88467, 28007,
    // -34686, -69049, -41677, 88535, -8217, 68060, -51280, 28971, 49088, 49235,
    // 26905, -81117, -44888, 40623,
    // 74337, -24662, 97476, 79542, -72082, -35093, 98175, -61761, -68169, 59697,
    // -62542, -72965, 59883,
    // -64026, -37656, -92392, -12113, -73495, 98258, 68379, -21545, 64607, -70957,
    // -92254, -97460, -63436,
    // -8853, -19357, -51965, -76582, 12687, -49712, 45413, -60043, 33496, 31539,
    // -57347, 41837, 67280, -68813,
    // 52088, -13155, -86430, -15239, -45030, 96041, 18749, -23992, 46048, 35243,
    // -79450, 85425, -58524, 88781,
    // -39454, 53073, -48864, -82289, 39086, 82540, -11555, 25014, -5431, -39585,
    // -89526, 2705, 31953, -81611,
    // 36985, -56022, 68684, -27101, 11422, 64655, -26965, -63081, -13840, -91003,
    // -78147, -8966, 41488, 1988,
    // 99021, -61575, -47060, 65260, -23844, -21781, -91865, -19607, 44808, 2890,
    // 63692, -88663, -58272, 15970,
    // -65195, -45416, -48444, -78226, -65332, -24568, 42833, -1806, -71595, 80002,
    // -52250, 30952, 48452,
    // -90106, 31015, -22073, 62339, 63318, 78391, 28699, 77900, -4026, -76870,
    // -45943, 33665, 9174, -84360,
    // -22684, -16832, -67949, -38077, -38987, -32847, 51443, -53580, -13505, 9344,
    // -92337, 26585, 70458,
    // -52764, -67471, -68411, -1119, -2072, -93476, 67981, 40887, -89304, -12235,
    // 41488, 1454, 5355, -34855,
    // -72080, 24514, -58305, 3340, 34331, 8731, 77451, -64983, -57876, 82874,
    // 62481, -32754, -39902, 22451,
    // -79095, -23904, 78409, -7418, 77916 };

    // int[] nums2 = new int[nums.length];
    // for (int i = 0; i != nums.length; i++) {
    // nums2[i] = nums[i];
    // }
    // ThreeSum t = new ThreeSum();
    // long start, end;
    // start = System.currentTimeMillis();
    // List<List<Integer>> ans1 = t.binSearch(nums);
    // end = System.currentTimeMillis();
    // f1.println((end - start));
    // start = System.currentTimeMillis();
    // List<List<Integer>> ans2 = t.theAnswer(nums2);
    // ans2.sort(t.new SortAns());
    // end = System.currentTimeMillis();
    // f2.println((end - start));
    // int count = 1;
    // for (List<Integer> x : ans1) {
    // f1.print(count + "\t");
    // for (int y : x) {
    // f1.print(y + "\t");
    // }
    // f1.print(ThreeSum.sum(x) + "\n");
    // count++;
    // }
    // count = 1;
    // for (List<Integer> x : ans2) {
    // f2.print(count + "\t");
    // for (int y : x) {
    // f2.print(y + "\t");
    // }
    // f2.print(ThreeSum.sum(x) + "\n");
    // count++;
    // }
    // }

}