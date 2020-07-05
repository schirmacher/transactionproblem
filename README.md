This example code illustrates a transaction problem.

I would like to count user likes for review articles. There are several 'Review' objects, each one having a 'likes' counter. Users can increase/decrease the counter by a web request. After the increase/decrease command, the new 'likes' counter value is displayed to the user. If two users send a web request at the same time, then one of the request must wait until the other one has completed, and both users get different 'likes' counter values.

To test this race condition, I called Thread.sleep(3000) so that I can run the same web request manually from two different browsers.

My first approach was to use the @Transactional(isolation = Isolation.SERIALIZABLE) annotation. The code retrieves the Review entity, updates the 'likes' counter value and saves it.

I expected that if my ReviewServiceImpl method was called from my two manual web requests, the second call would arrive while the first call is still in the sleep() function, the first transaction is still open and the second call can’t even start the transaction until after the first call is completed and the first transaction is committed.

However both browser windows display the same 'likes' counter value so there is an error somewhere.

My second approach was to use a database update query as suggested in Spring, JPA, and Hibernate - how to increment a counter without concurrency issues . This works better as no 'likes' updates get lost. But the pages display the value before the update so the controller somehow retrieves outdated data.
