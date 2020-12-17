// Name: Zachary Bensemana
// McGill ID: 260863976


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KDTree implements Iterable<Datum> {

    KDNode rootNode;
    int k;
    int numLeaves;

    // constructor

    public KDTree(ArrayList<Datum> datalist) throws Exception {

        Datum[] dataListArray = new Datum[datalist.size()];

        if (datalist.size() == 0) {
            throw new Exception("Trying to create a KD tree with no data");
        } else
            this.k = datalist.get(0).x.length;

        int ct = 0;
        for (Datum d : datalist) {
            dataListArray[ct] = datalist.get(ct);
            ct++;
        }

        //   Construct a KDNode that is the root node of the KDTree.

        rootNode = new KDNode(dataListArray);
    }

    //   KDTree methods

    public Datum nearestPoint(Datum queryPoint) {
        return rootNode.nearestPointInNode(queryPoint);
    }


    public int height() {
        return this.rootNode.height();
    }

    public int countNodes() {
        return this.rootNode.countNodes();
    }

    public int size() {
        return this.numLeaves;
    }

    //-------------------  helper methods for KDTree   ------------------------------

    public static long distSquared(Datum d1, Datum d2) {

        long result = 0;
        for (int dim = 0; dim < d1.x.length; dim++) {
            result += (d1.x[dim] - d2.x[dim]) * ((long) (d1.x[dim] - d2.x[dim]));
        }
        // if the Datum coordinate values are large then we can easily exceed the limit of 'int'.
        return result;
    }

    public double meanDepth() {
        int[] sumdepths_numLeaves = this.rootNode.sumDepths_numLeaves();
        return 1.0 * sumdepths_numLeaves[0] / sumdepths_numLeaves[1];
    }

    class KDNode {

        boolean leaf;
        Datum leafDatum;           //  only stores Datum if this is a leaf

        //  the next two variables are only defined if node is not a leaf

        int splitDim;      // the dimension we will split on
        int splitValue;    // datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue

        KDNode lowChild, highChild;   //  the low and high child of a particular node (null if leaf)
        //  You may think of them as "left" and "right" instead of "low" and "high", respectively

        KDNode(Datum[] datalist) throws Exception {

            /*
             *  This method takes in an array of Datum and returns
             *  the calling KDNode object as the root of a sub-tree containing
             *  the above fields.
             */

            //   ADD YOUR CODE BELOW HERE


//            creates leaf datum if datalist has only one datum
            if (datalist.length == 0) {
            } else if (datalist.length <= 1) {
                //creates leaf datum if datalist has only one datum
                this.leaf = true;
                this.leafDatum = datalist[0];
                numLeaves++;

            } else {



                if (!leaf) {

//                  Makes clones of first entry to assign to max and min
                    int[] range = new int[k];
                    int[] max = datalist[0].x.clone();
                    int[] min = datalist[0].x.clone();

//                      Loops through each entry in each dimension
                    for (int i = 1; i < (datalist.length); i++) {
                        for (int j = 0; (j < k); j++) {

//                      Finds the largest range among the data points by assigning the max point and min point to the arrays max and min
                            if (datalist[i].x[j] > max[j]) {
                                max[j] = datalist[i].x[j];
                            }
                            if (datalist[i].x[j] < min[j]) {
                                min[j] = datalist[i].x[j];
                            }

//                      Makes a range index using the max and min
                            range[j] = max[j] - min[j];
                        }
                    }

//                  Destroys duplicates by seeing if the range is 0 in all cases (meaning all the points are the same)
                    int counter = 0;
                    for (int i : range) {
                        if (i == 0) {
                            counter++;
                        }
                    }
                    if (counter==range.length) {
                        Datum[] duplicateDestroyer = new Datum[1];
                        duplicateDestroyer[0] = datalist[0];
                        datalist = duplicateDestroyer;
                        leaf = true;
                        leafDatum = datalist[0];
                        numLeaves++;
                    } else {

                        leaf = false;

                        //finds the split dim by seeing where the range is biggest
                        int maxRange = 0;
                        splitValue = 0;

                        for (int i = 0; i < k; i++) {
                            if (range[i] > maxRange) {
                                maxRange = range[i];
                                splitDim = i;
                            }
                        }

//                      Finds the split value by adding half the range to the min value
                        splitValue = min[splitDim] + (range[splitDim] / 2);

                        Datum[] splitDatumHigh;
                        Datum[] splitDatumLow;

//            Finds the length of each array
                        int splitLengthLow = 0;
                        int splitLengthHigh = 0;
                        for (int i = 0; i < datalist.length; i++) {
                            if (datalist[i].x[splitDim] <= splitValue) {
                                splitLengthLow++;
                            } else {
                                splitLengthHigh++;
                            }
                        }
                        splitDatumHigh = new Datum[splitLengthHigh];
                        splitDatumLow = new Datum[splitLengthLow];

//            Sorts the points above and below the splitValue
                        int lowIndex = 0;
                        int highIndex = 0;
                        for (int i = 0; i < datalist.length; i++) {
                            if (datalist[i].x[splitDim] <= splitValue) {
                                splitDatumLow[lowIndex] = datalist[i];
                                lowIndex++;
                            } else {
                                splitDatumHigh[highIndex] = datalist[i];
                                highIndex++;
                            }
                        }

//            recursively creates new nodes
                        lowChild = new KDNode(splitDatumLow);
                        highChild = new KDNode(splitDatumHigh);

                    }
                }
            }
        }


        //   ADD YOUR CODE ABOVE HERE

        public Datum nearestPointInNode(Datum queryPoint) {
            Datum nearestPoint, nearestPoint_otherSide;

            //   ADD YOUR CODE BELOW HERE
            nearestPoint_otherSide = null;


            Datum answer;
            long distance;
            long distance_otherSide = 0;
            boolean otherSide = false;
            long distSplitDim;

//            Checks for null querypoint
            if (queryPoint==null)
                return null;
//            Returns point if it is a leaf
            else if (this.leaf) {
                nearestPoint = this.leafDatum;
                return nearestPoint;

//              Checks to see if queryPoint is below or above split dim
//                This case is for the low child
            }else if (queryPoint.x[this.splitDim] <= this.splitValue) {
                distSplitDim = ((long) (queryPoint.x[this.splitDim] - this.splitValue)) * ((long) (queryPoint.x[this.splitDim] - this.splitValue));
//                Recursive call
                answer =  this.lowChild.nearestPointInNode(queryPoint);
                distance = distSquared(answer, queryPoint);

//              Compares distance to split dim ON EACH LEVEL with distance to nearestPoint
                if (distSplitDim < distance) {
//                    IF greater, calls the nearest point on the other side
                    nearestPoint_otherSide =  this.highChild.nearestPointInNode(queryPoint);
                    distance_otherSide = distSquared(nearestPoint_otherSide, queryPoint);
                    if (distance_otherSide < distance)
                        otherSide = true;
                }
//                  This case is for the high child
            } else {
                distSplitDim = ((long) (queryPoint.x[this.splitDim] - this.splitValue)) * ((long) (queryPoint.x[this.splitDim] - this.splitValue));

                answer = this.highChild.nearestPointInNode(queryPoint);

                distance = distSquared(answer, queryPoint);

                if (distSplitDim < distance) {
                    nearestPoint_otherSide =  this.lowChild.nearestPointInNode(queryPoint);
                    distance_otherSide = distSquared(nearestPoint_otherSide, queryPoint);
                    if (distance_otherSide < distance)
                        otherSide = true;
                }

            }

            if (otherSide) {
                return nearestPoint_otherSide;
            } else {
                return answer;
            }


            //   ADD YOUR CODE ABOVE HERE
        }



        // -----------------  KDNode helper methods (might be useful for debugging) -------------------


        public int height() {
            if (this.leaf)
                return 0;
            else {
                return 1 + Math.max(this.lowChild.height(), this.highChild.height());
            }
        }

        public int countNodes() {
            if (this.leaf)
                return 1;
            else
                return 1 + this.lowChild.countNodes() + this.highChild.countNodes();
        }

        /*
         * Returns a 2D array of ints.  The first element is the sum of the depths of leaves
         * of the subtree rooted at this KDNode.   The second element is the number of leaves
         * this subtree.    Hence,  I call the variables  sumDepth_size_*  where sumDepth refers
         * to element 0 and size refers to element 1.
         */

        public int[] sumDepths_numLeaves() {
            int[] sumDepths_numLeaves_low, sumDepths_numLeaves_high;
            int[] return_sumDepths_numLeaves = new int[2];

            /*
             *  The sum of the depths of the leaves is the sum of the depth of the leaves of the subtrees,
             *  plus the number of leaves (size) since each leaf defines a path and the depth of each leaf
             *  is one greater than the depth of each leaf in the subtree.
             */

            if (this.leaf) {  // base case
                return_sumDepths_numLeaves[0] = 0;
                return_sumDepths_numLeaves[1] = 1;
            } else {
                sumDepths_numLeaves_low = this.lowChild.sumDepths_numLeaves();
                sumDepths_numLeaves_high = this.highChild.sumDepths_numLeaves();
                return_sumDepths_numLeaves[0] = sumDepths_numLeaves_low[0] + sumDepths_numLeaves_high[0] + sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
                return_sumDepths_numLeaves[1] = sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
            }
            return return_sumDepths_numLeaves;
        }

    }


    public Iterator<Datum> iterator() {
        return new KDTreeIterator();
    }

    private class KDTreeIterator implements Iterator<Datum> {

        //   ADD YOUR CODE BELOW HERE
        KDNode cur=rootNode;
        int index=0;
        List<Datum> list1 = new ArrayList<Datum>();

        public KDTreeIterator(){
            inOrder(cur);
        }

        @Override
        public boolean hasNext() {
            return (index<list1.size());
        }

        @Override
        public Datum next() {
            if (this.hasNext()){
                return list1.get(index++);
            }
            else return null;
        }

// Recursively goes through every node. If null, goes back up and sees next child. If Leaf, adds to leaf list
        private void inOrder(KDNode search) {
            if (search!=null){
                if (search.leaf) {
                    list1.add(search.leafDatum);
                }
                inOrder(search.lowChild);
                inOrder(search.highChild);
            }
            }

        @Override
        public void remove() {

        }
    }


}



