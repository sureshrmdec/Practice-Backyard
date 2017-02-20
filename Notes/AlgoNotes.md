Handshaking lemma :
    A statement that every finite undirected Graph has an even number of vertices
    with odd degree. The sum of degree of vertices is twice the number of edges.

Full Binary Tree :
    A tree is said to be full binary tree if every node has 0 or 2 children
    In full binary tree, Number of leaf nodes = number of internal nodes + 1;
    A binary tree with depth k which has exactly 2^k-1 nodes is called full
    binary tree

Internal Node :
    Nodes which are not leaves are called internal nodes, while Nodes
    with no children is called leaf or external node.

Complete Binary Tree :
    A binary tree is complete binary tree if all levels are
    completely filled except the last and for last level all the keys are as left
    as possible.

Perfect Binary Tree :
    A binary tree in which all internal nodes have two children  and all the leaves
    are at same level.

    A perfect binary tree of height h (height - number of nodes on path from
    root to leaf) is 2^h-1

Balanced Binary Tree:
    A tree that provide search, insert and delete in O(Log n) where n = number of nodes
    AVL tree maintains O(Log n) by ensuring that difference between height of left and
    right subtree is 1.

    Number of tree , a node set generate - follow catalan numbers
    (1, 1, 2, 5, 14) T(n) = (2n)!/(n+1)!n!

Dynamic Programming:
    Overlapping Solutions : Using solution of previously solved subproblems to
    solve larger problems
    Optimal Substructure: A larger optimal solution compromised of two smaller
    optimal solutions such as shortest distance from A->C is the shortest distance
    from A->B and B->C.

Binary Search Tree:
    Left subtree should contain value less than right subtree and their should
    be no duplicate values.
    Such ordering provides a fast way to access minimum and maximum

    **class Heap<T extends Comparable<T>>, extends used to refer interface here **
