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
    question it's about finding the shortest path between two nodes in graph. We will use
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
