Approach to solve System Design Problems:
    1.Scope of the Problems
        Make a list of major feature or use cases : (Example : tinyURL)
            1. Shorting URL to tinyURL
            2. User account management
            3. Retriving the URL associated with tinyURL
            4. analytics for URL

    2. Make resaonable Assumptions
        1. What can be number of user request we can handle
        2. what are the memory and system constraint we have

    3. Draw the major component
        1. How front end server communicate with back end server and how another
        set of serverse fetch the data, another set of servers doing analytics

        **Explain with the example how system will work when user enters the request**

    4. Identify the key issues
        1. Some urls are accessed frequently and hitting database everytime is not
        efficient

    5. ReDesign for key issues
        1. Think how you can eliminate the key issues such as you can use cache
        and other mechanism to storing frequently used data


Approach to solve Algorithm problem that scale
    1. Scope of the problem
        Make a list of major features and Scope

    2. Think how can you solve this problem on single machine

    3. Get Real and think how problem will take shape ,when you will divide the data
       and how different components will be communicated

Points to consider :
    1. look out for failure
        Any system can fail, plan for situation when component are failing

    2. Read heavy vs Write heavy
        if application is read havy then think something of cache ,if something is
        write heavy think of queuing up writes.

    3. Availability and reliability :
        Availability defines uptime for a given system and reliability is probability
        that system will perform in correct and be operational for a certain amount of Time

    4. Security Threats:
        Denial of Service, Cross scripting attacks

Key Concepts :
    Horizantal vs vertical Scanning:
        Vertical scalling means adding more resources to a specific node such as adding
        to specific server by Horizantal scanning define adding a new node to distribute the load

        Vertical scalling is easier than Horizantal scalling

    load Balancer :
        A distribution server which will direct incoming request to needed backend server
        while ensuring that no server that blocked with high request and load get distributed
        evenly

    Database denormalization and NO SQL:
        denormalization is process of adding redundant Information to speed up the read
        joins are slow can store result as table itself or use no sql DB which scale better

    Database Partioning:

        Vertical Partioning:
            Also called Partioning by feature, such as one server consisting of all profiles
            of users, other hold all the messages. The problem is message server can  be
            over occupied at time while other are having very less traffic. second problem
            is if any of the partition got very big then you need to repartition using
            another scheme.

        Key Based (Hash Based) partioing :
            some part of data to partition ,for example list with user ids- you can
            divide into n servers each holding some user data in whole.

        Directory based partitoing:
            Simple lookup at main server which will tell which server holds the data
            but this can become single point of failure and you have one table accessing
            every time  a user request comes in.


    Caching:
        Sit's between application layer and data store

    Asynchronous Processing and Queues :
        Slow operations should be done asynchronously.other wise user will be stuck
        to wait

    Networking Metrices :
        Bandwidth:
            Maximum amount of data that can be transfered in a unit of time.typically
            bits per second.

        ThroughtPut: Bandwidth define maximum amount of data that can be tranfered in
        a unit of time.throughput defines actual data that gets tranfered.

        Latency : dealy between sender sending the user request data and reciever
        getting it.

    Map Reduce :
        A  parallel programming paradigm to process large amount of data
        Map : takes some data and emit (key, value) pair
        Reduce: take (key, value) pair from Map and reduce them in some way generating
        new key and value
        System process shuffle will put all data associated with given key into single machine
        and pass that to reduce method

        /**
        *Word count program using Map Reduce

        void map(String name, String document) :
            for each w in document:
            emit(w, 1)

        void reduce(String word, Iterator partialCount):
        int sum = 0
        for each count in partialCount
            sum += count
        emit(word, sum)
        */
Additonal Nots on scalability :
    1. Clones : Every server stores the same codebase and no application server holds data such as
    user profiles and sessions

    Q: What is session inconsistency ?
    A: If individual application server is holding session data ,then with new request, it maye
    end up to new server and it may ask to user to sign up again

    Session should be store in centeralized server/persistent cache on different server not on
    any of the application server.

    if your first bottleneck is over- which is handling large number of concurrent request then
    another bottleneck for heavy data writing applications is database - if you choose to have
    relational DB, then create a master slave replication (read from slaves and write to master)
    -denormalization, sharing,SQL Tuning

    Another way is,denormalization to be done from begining and reduce number of joins query.
    Couch or MongoDB helps to perform join in application code than DB level.

    Cache resides in buffering layer between application layer and data storage
    Query Caching(Cache query and result in cache but in some cases query is not request
    more than one) vs Object Caching(keep object instance and all updates to object are added
    to cache such as you comment on post)

    Asynchronous Behaviour : all the heavy work is done in advance such as cron job
    and then results are rendered

    Behind the scene update the dashboard or allow user to explore the website but
    inform them when job is processed

    Database sharding :
        Federated Model : data is stored in multiple server and you search all and
        combine and return the result
        Sharding is a process of creating a group of server on cheap linux machines

        Advantage :
        High



    Q: What is the difference between web server and application server
    A: Webserver is designed to serve http content, App server can server http and
    other protocol content. In typical setting webserver will return static content
    while application server will return dynamic content

    Application Server exposes business logic and process
    Web server, serve content using http protocol


Example 1:
    Given a list of millions of documents, how would you find all documents that contain a list of words? The words
    can appear in any order, but they must be complete words. That is, "book" does not match "bookkeeper."

    Step 1: Scope of the problem
        1. scalable
        2. efficient
        3. secure

    Sol 1: Give client address of FTP server to download the data but that it transfer
    responsibilty on client to parse the data and get stock price for open and close day

    Sol 2: for each client request, create a connection to DB that will give added advantage
    for getting query based result such as which stock higher than all given stock

    Sol 3: Create an Web API that will be called by clients with parameter such as stock name
    and return the result in json/xml format which can be parsed by any client language


Example 2:
    How would you design the data structures for a very large social network like
    Facebook or Linkedln? Describe how you would design an algorithm to show the shortest path
    between two people (e.g., Me-> Bob-> Susan-> Jason-> You).

    Sol: the approach is to represent each person as node and it's various attribute such as
    birth date ,email address and other as attribute to given node. To answer the second
    question it's about finding the shortest path between two nodes in graph We will use
    BFS approach beacause DFS can check in depth first and it may be the case that you are
    just one level away but DFS has searched million of node in depth.

    You can further use bidirectional BFS to support search from both node and
    if they collide ,you can ensure that they have a path and distance is sum of distance
    from both end.

    Part 2: How would you scale this to million of users.
    1. You can divide the data into different set of machines and for each node freiend list
       you store the machine number in hashMap (Like person, MachineNumber) and then search
       for that machine.

       You can further optimize the result by grouping machine together by assumption
       that most friends live in same country or let's visit all the friends in given machine
       for given person before moving to next machine.



Example 3: if you are desiging a webCrawler ,how would you avoid the loop ?
    Sol:The idea is to first understand the problem ,how something went into infinite
    loop, if we represent internet as graph then it's the cycle which cause infinite loop
    So we can have a hashMap which maitain history of all the visited nodes, and if find
    that we are visiting a node that is already visited

    Now second question is what it mean for two URL to have same , Are it mean they are
    same in content vs same in URL : similarity detection
    1. Define the maximum limit of pages for given seed web pages
    2. Define the depth till which a page connection will be traversed

Example 4: You have 10 billion URLs. How do you detect the duplicate documents? In this
case, assume "duplicate" means that the URLs are identical.

    Sol :
        1. Define the use case and scope : what is mean by duplicate - are they duplicate
        by url name or by content
        2.Try to solve this problem as if you are able to store data in memory
        Create a hashMap that will store url to count and display the url that are
        occured more than once

        Part 2: Scale the solution
        1. if a single machien was there : then divide the url into set of files
        where each files containing url generated by specific hash such as hash(url) /4000;

        2. if we are allowed to have multiple machine then instead of sending to file we can
        map that same hash generated file to different machines.

Example 5:
Imagine a web server for a simplified search engine. This system has 100 machines to
respond to search queries, which may then call out using processSearch(string query)
to another cluster of machines to actually get the result. The machine which responds to a given
query is chosen at random, so you cannot guarantee that the same machine will always respond to
the same request. The method processSearch is very expensive. Design a caching mechanism
to cache the results of the most recent queries. Be sure to explain how you would update the cache
when data changes.

    Sol :
    1. Define the scope and use case for the problem
        What will be cached
        What will be the size of cache
        What will be cache behaviour for item that are not frequently used

        Requirement for a good cache:
        1. Efficient look up
        2. Expiration of old data so it can be replaced with new data
        HashMap + LinkedList- hashmap will give efficient lookup while LinkedList
        will help to update recent accessed data to top of linked List and in that way
        tail will hold least recently used data (Reference : LinkedList.java @LRUCache)

        Assumption:
        1. All operation happens on initial machine, only processSearch is passed to
        other machines
        2. intra network of machine to machine is quick.

        Design a cache system for single machine :
        LRU Cache

        Scaling up the solution:
            1. Each machine has it's cache , upside - very quick and downside a entry
            on other machine which was already cached in other machine , need to cache again
            creating a redundancy in result. Problem is too many repeat queries will be treated
            as single query.

            2.Each machine has the copy of cache. and when new data comes in you update all
            the entries in N server but drawback is you are using N server space for every single
            entry

            3. Each machine store a segment of the cache:
                Each machine will hold some amount of cache data but which machine to
                direct for request can be determined by generating hashValue (hashQuery) % N
                if it return null, call processSearch(query) and update that machine cache
                because next time when hash will be generated for same machine ,it already hold
                the data

            Updating results when content changes:
                1. The content of URL changes
                2. Ordering of result change in response of changed page rank
                3. Timeout, preodic update for most popular queries


Example 6:A large eCommerce company wishes to list the best-selling products, overall and by
category. For example, one product might be the #1056th best-selling product overall but the #13th
best-selling product under "Sports Equipment" and the #24th best-selling product under "Safety:·
Describe how you would design this system.

    1. State use case and scope of problem
        Consider Amazon which have X number of categories and best selling list for all the categories
        Define what sales rank mean, is mean for this month, weak or for all Time
        A product can be multiple categories


    2. Define Assumptions :
        Precision is supported with some error such as Updates on preodic basis such as
        hourly basis and updates are more important for pouplare items compared to less popular items


    3. Draw Major Components:
        Frontend - Backend Server -(Analytics Engine) - Database
        Performing sales calculation at each hour will be very expensive why not stores details in DB
        either through proper table configuration or performing PL/SQL block in database to Calculate
        total sales and top selling item in each category

        Create a product table with each product id have sales column for monday ,tuesday and other
        then create a table that provide product id to catalog mapping

        Do a batch DB write than writing with every purchase

        Joins are expensive


Example 7: Design a system to download videos from Youtube ?
    1. Define scope and use case for system
    A:
        1.Will it be a web app where user can enter the url and downloadere
        2.Will there be a support for Channel video downloads
        3.Will we provide different format downloads
        4.Will we have feature to user to create profile and have history
        5. Are we suppose to have all youtube videos
    So user will enter a query either a signle video/channel then able to
    download in different formats and have feature to create profile

    Assumptions:
    1. System is holding data for specific category
    2. Maximum number of users - 100


    3. Draw the major component
       Front end application - > Server - > Database
       Q: How we get data from Youtube and store in DB ?
       A: Typical Youtube DB is MySQL but we only need to maintain URL to video mapping
       so Dynamo DB will be a great fit. We will crawl the data at regular interval to
       fetch newly added data to our DB

       How it's done is reverse engineering the youtube url and determine the
       location of FLV file.

    4. Describe the bottleneck
    A:
        1. When there are too many request coming in.
        A: We can create a set of application server connected to Load balancer to
        distribute the traffic.
        2. How to scale the solutions in storage ?
        A: We can first you the cache popular videos so it will reduce DB requests
           We can search data using hash in Dynamo DB which is a key value piar.




















Harvad Scalability Lecture:
    Web Hosting Companies provide shared host: dynamic IP address
    VPses : A large machine is distributed into smaller section with which
    you are exclusively assigned to that portion.

    QuadCore : Your compute can do four things at once
    If you require data to be written more in database you can use more efficient
    hard drive such as SAS with hihger read/write ratio

    Horizantal Scalling : distribute input request to all web server through load Balancer
    so all backend server can have private IP address while load balancer have public IP
    address which will be used to send and return request.

    Then load balancer will decide which server to send the requst such as least used
    -Alternative : You can have one server for image and one for html

    Load Balancer as DNS Server where it will get a request and send that request to
    backend Server 1 then 2 and so on in round robin fashion

    session are specific to given machine but in distributed mode , if you are on different
    machine they will ask you to login again
