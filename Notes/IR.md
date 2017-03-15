Precision : relevant item retrieved / all relevant items
Recall : relevant items retrieved / all retrieved items
A : set of relevant documents
B : set of retrieved documents

Recall : |A intersection B| / |A|
Precision : |A intersection B| / |B|

Harmonic mean : F : 2RP/(R + P) -> F score aggregated values of system
Mean average precision : average precision over all set of queries
Discounted cumulative gain:

How evaluation is done at search engine level :
A/B testing ,Studies of behavior in lab
Google Approach : Precision evaluation, live traffic experiment
Using query log to understand which result has been clicked by user and updating
it's ranking

Crawling :
    A program that visit web pages in an organized way.
    googleBoot, yahooSlurp
    How to Crawl ?
        Qualtiy - getting best page first, Efficiency - how to avoid duplication,
        Etiquette - behave polietly with not to distrub website performance.

    Start with seed url and put all the connected urls on queue and keep extracting.
    Distribute the crawling using multiple threads
    Robot.txt file contain pages which website don't want us to visit

    Crawling Algorithm :
        Intialize queue with set of known URL's
        loop until queue is empty or time limit exhausted
        if L is alreday vistied/or not html page then continue the loop
        index the page P, parse P to get new link N, append N to queue
        How things added to queue - BFS, DFS, or own heuristic such as pages with
        high in order degree

        One way to determine if URL already seen - first hash on host/domain name
        use a trie data structure to determine if path/resource is same as url One

        Aware of Spider Trap :
        DNS caching to retain IP domain name of mapping previously discovered
        Centralized crawler with child crawler and child crawler update to master
        Distributed crawler : dynimcally communication to provide eventual consistency

        LastModified property of page to determine do we need to reindex the page

Duplicats :
    Exact duplicate : based on mirroring, different host -use SHA-1 MD-5
    Near duplicats - cosine similarity, jacard similarity, Hamming distance, shingling
    Shingling : S(A,w) : 4 word breaking into different parts
    Resemblance : size of(A intersection B)/ size of( S(A,w)) union S(B,w )

Information Retrival :
    How to index set of documents
    How to reterive relevant docuemnts ?

    Strip the unwanted characters/markeup (e.g html )
    break into tokens- > stem token to root word
    Remove common stop word
    Create a inverted Index : Keyword - > List of document containing it

    Boolean Model : AND, OR but very rigid, return all matched document, don't rank them
    Vector space model : each document will have terms and each term have it's weight frequency

    **Term Frequency : how frequent a term is appreaing in document,
    **TF-IDF : how frequent a term is occuring across all document in corpus
    - A term occuring frequently in the document but rarely in the rest of collection is
    the highest weight

    Cosine Similarity : if we treat document as set of features and create a vector
    space then it will define inner produce
    similarity(dj, q) : summation (wij. wiq) : weight of term i in document j
    wiq: weight of term i in query q


Tokenization , Mapping words to root word, furthermore using stemming algorithm to Remove
stop words and very common words such as the,and
Algorithm : Soundex algorithm and Poter stemming algorithm

Term doucment Frequency :
<Keyword> , Number of document in which occuring => List containing each document and count
