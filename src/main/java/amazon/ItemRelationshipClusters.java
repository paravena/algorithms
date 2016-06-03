package amazon;

import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ItemRelationshipClusters {
    private static ItemRelationshipClusters irc;

    public static void main(String[] args) {
        //readInput(System.in);
        readInput(ItemRelationshipClusters.class.getClassLoader().getResourceAsStream("item_relationship_clusters_input_3.txt"));
    }

    public static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        double affinityThreshold = scan.nextDouble();
        scan.nextLine();
        int numberOfLines = scan.nextInt();
        scan.nextLine();

        irc = new ItemRelationshipClusters(affinityThreshold);

        for (int i = 0; i < numberOfLines; i++) {
            String line = scan.nextLine();
            String[] tokens = line.split("\\s");
            String item1 = tokens[0];
            String item2 = tokens[1];
            double itemsAffinity = Double.parseDouble(tokens[2]);
            irc.registerItemsIfTheyAreAbsent(item1, item2);
            if (irc.itemsHaveAffinity(itemsAffinity)) {
                irc.itemUnion(item1, item2);
            }
        }
        System.out.println(irc.findLargestCluster());
    }

    private double affinityThreshold;
    private Map<String, String> itemClusters = new HashMap<String, String>();
    private Map<String, Integer> itemClustersCounter = new HashMap<String, Integer>();

    public ItemRelationshipClusters(double affinityThreshold) {
        this.affinityThreshold = affinityThreshold;
    }

    public boolean itemsHaveAffinity(double itemsAffinity) {
        return itemsAffinity >= affinityThreshold;
    }

    public void itemUnion(String item1, String item2) {
        String itemCluster1 = findItemCluster(item1);
        String itemCluster2 = findItemCluster(item2);

        itemClusters.put(item2, item1);
        itemClustersCounter.put(itemCluster1,
                itemClustersCounter.get(itemCluster1) + itemClustersCounter.get(itemCluster2));
    }

    public String findItemCluster(String item) {
        while (!itemClusters.get(item).equals(item)) {
            item =  itemClusters.get(item);
        }
        return item;
    }

    public void registerItemsIfTheyAreAbsent(String item1, String item2) {
        itemClusters.putIfAbsent(item1, item1);
        itemClusters.putIfAbsent(item2, item2);
        itemClustersCounter.putIfAbsent(item1, 1);
        itemClustersCounter.putIfAbsent(item2, 1);
    }

    public String findLargestCluster() {
        Comparator<? super Map.Entry<String, Integer>> maxValueComparator =
                (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue());
        Optional<Map.Entry<String, Integer>> maxValue = itemClustersCounter.entrySet()
                .stream().max(maxValueComparator);

        String key = null;
        Integer max;
        if (maxValue.isPresent()) {
            max = maxValue.get().getValue();
            key = maxValue.get().getKey();

            // If there are more than one
            List<Map.Entry<String, Integer>> entries = itemClustersCounter.entrySet()
                    .stream().filter(entry -> entry.getValue().equals(max)).collect(Collectors.toList());
            if (entries.size() > 1) {
                System.out.println("more than one");
                Comparator<? super Map.Entry<String, Integer>> alphabeticComparator =
                        (entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey());

                Optional<Map.Entry<String, Integer>> min = entries.stream().min(alphabeticComparator);
                if (min.isPresent()) {
                    key = min.get().getKey();
                }
            }
        }
        return key;
    }
}
