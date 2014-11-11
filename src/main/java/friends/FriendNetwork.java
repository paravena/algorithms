package friends;

import java.util.*;

class Friend {
    protected String name;
    protected Friend friendOf;
    protected Set<Friend> friends = new HashSet<Friend>();
    protected boolean visited;
    protected int distance;

    Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Friend getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Friend friendOf) {
        this.friendOf = friendOf;
    }

    public void addFriend(Friend friend) {
        friends.add(friend);
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return !(name != null ? !name.equals(friend.name) : friend.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}


public class FriendNetwork {
    Map<String, Friend> friends = new HashMap<String, Friend>();

    public void addMember(Friend friend) {
        friends.put(friend.getName(), friend);
    }

    public void addFriendOfMember(String memberName, String friendName) {
        Friend member = lookupFriend(memberName);
        if (member == null) {
            member = new Friend(memberName);
            addMember(member);
        }
        Friend friend = lookupFriend(friendName);
        if (friend == null) {
            friend = new Friend(friendName);
            addMember(friend);
        }
        // simetric
        member.addFriend(friend);
        friend.addFriend(member);
    }

    public Friend lookupFriend(String name) {
        return friends.get(name);
    }

    public void readInput(String[] inputArr) {
        if (inputArr == null || inputArr.length == 0) return;
        for (String input : inputArr) {
            String[] tokens = input.split(":");
            if (tokens == null) return;
            String memberName = tokens[0];

            // adding friends of member
            if (tokens.length > 1) {
                String friendsInput = tokens[1];
                String[] friendsNames = friendsInput.split(",");
                for (String friendName : friendsNames) {
                    addFriendOfMember(memberName, friendName);
                }
            }
        }
    }

    public void printOutput(String rootMemberName) {
        LinkedList<Friend> queue = new LinkedList<Friend>();
        Friend rootMember = lookupFriend(rootMemberName);
        Map<Integer, List<Friend>> friendLevels = new HashMap<Integer, List<Friend>>();
        rootMember.setDistance(0);
        rootMember.setFriendOf(null);
        queue.add(rootMember);
        while (!queue.isEmpty()) {
            Friend currentMember = queue.removeFirst();
            for (Friend friend : currentMember.getFriends()) {
                if (!friend.isVisited() && !friend.equals(rootMember)) {
                    int distance = currentMember.getDistance() + 1;
                    friend.setDistance(currentMember.getDistance() + 1);
                    if (friendLevels.get(distance) == null) {
                        friendLevels.put(distance, new ArrayList<Friend>());
                    }
                    friendLevels.get(distance).add(friend);
                    friend.setFriendOf(currentMember);
                    queue.add(friend);
                }
                friend.setVisited(true);
            }
        }
        for (Integer friendLevel : friendLevels.keySet()) {
            System.out.println(friendLevel + " " + printArrayOfFriends(friendLevels.get(friendLevel)));
        }
    }

    private String printArrayOfFriends(List<Friend> friends) {
        String result = "[";
        for (Friend friend : friends) {
            result += friend + ",";
        }
        result +="]";
        return result;
    }

    public static void main(String[] args) {
        FriendNetwork fn = new FriendNetwork();
        fn.readInput(new String[] {"A:B,C,D", "B:A,D,E", "C:E,B", "A"});
        fn.printOutput("A");
    }
}
