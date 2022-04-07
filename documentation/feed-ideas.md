
# Implementation for caching user feed queues:

```java
class Post {
    Long postId;
    Integer numberOfUsersSubscribed;
    Set<FeedPost> subscribers;

    public Post(Long postId, Integer numberOfUsersSubscribed) {
        this.postId = postId;
        this.numberOfUsersSubscribed = numberOfUsersSubscribed;
    }

    void updateRankingToAllSubscribers(Integer zmiana) {
        subscribers.forEach((
                feedPost -> feedPost.setRanking(feedPost.getRanking() + zmiana)
        ));
    }
}
class FeedUserQueue {
    Long userId;
    HashMap<Byte, List<FeedPost>> queue;
}
class Feed {
    Set<Post> allFeedPosts;
    List<FeedUserQueue> userQueues;

    Feed(Set<Post> allFeedPosts, List<FeedUserQueue> userQueues) {
        this.allFeedPosts = allFeedPosts;
        this.userQueues = userQueues;
    }

    void addPostToFeeds(PostDto postDto) {
        Post addedPost = new Post(postDto.postId, postDto.userIdsAndRankings.size());
        allFeedPosts.add(addedPost);
        postDto.userIdsAndRankings.forEach((key, value) -> userQueues
                .get(Math.toIntExact(key))
                .queue
                .get(value)
                .add(new FeedPost(addedPost, value)));
    }
    void wywalZkolejkiUzytkownika(Long userId, FeedPost feedPost) {
        userQueues.get(Math.toIntExact(userId)).queue.get(feedPost.ranking).remove(feedPost.post);
        if (--feedPost.post.numberOfUsersSubscribed <= 0) {
            allFeedPosts.remove(feedPost.post);
        }
    }
    void zmienRankingPostaIUpdate(Post post, int zmiana) {
        post.updateRankingToAllSubscribers(zmiana);
    }
}
class FeedPost {
    Post post;
    Integer ranking;

    public FeedPost(Post post, Integer ranking) {
        this.post = post;
        this.ranking = ranking;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}

class PostEntity {
    Long userId;
    Long postId;
}

class PostDto {
   Long postId;
   HashMap<Long, Integer> userIdsAndRankings;
}
```

# Another feed idea with iterator:

```java
interface Iterator<T> {
    T getNext();
    boolean hasNext();
}
interface Collection<T> {
    Iterator<T> createIterator();
}
class UserQueueElement {
    UserQueueElement previous;
    UserFeedPost element;
    UserQueueElement next;

    public UserQueueElement(UserFeedPost element) {
        this.element = element;
    }

    public UserQueueElement getPrevious() {
        return previous;
    }

    public void setPrevious(UserQueueElement previous) {
        this.previous = previous;
    }

    public UserFeedPost getElement() {
        return element;
    }

    public void setElement(UserFeedPost element) {
        this.element = element;
    }

    public UserQueueElement getNext() {
        return next;
    }

    public void setNext(UserQueueElement next) {
        this.next = next;
    }
}
class UserQueue implements Collection<UserFeedPost>{
    final int queueSizeMax = 500;
    int queueSize = 0;

    UserQueueElement first;
    UserQueueElement last;

    UserQueue(List<UserFeedPost> posts){
        for (UserFeedPost p : posts)
            add(p);
    }


    public void add(UserFeedPost post){
        if(first == null){
            first = new UserQueueElement(post);
            last = first;
        }

        var current = first;
        while(current.next != null)
            current = current.next;

        UserQueueElement nextElement = new UserQueueElement(post);

        nextElement.previous = current;
        last = nextElement;
        current.next = nextElement;

        queueSize++;
    }

    public UserQueueElement getFirst(){
        return first;
    }

    @Override
    public Iterator<UserFeedPost> createIterator() {
        return new UserQueueIterator(this);
    }
}
class UserQueueIterator implements Iterator<UserFeedPost> {

    UserQueueElement current;

    UserQueue collection;

    public UserQueueIterator(UserQueue collection) {
        this.collection = collection;
        this.current = this.collection.getFirst();
    }

    @Override
    public UserFeedPost getNext() {
        UserQueueElement next = current.getNext();
        this.current = next;
        return next.getElement();
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}

class UserQueueService {

    Iterator<UserFeedPost> iterator;
    Collection<UserFeedPost> collection;

    public UserQueueService(Collection<UserFeedPost> collection) {
        this.collection = collection;
        this.iterator = this.collection.createIterator();
    }


}
```
