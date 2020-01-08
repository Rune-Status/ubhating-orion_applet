package client;


public final class Queue {

    private final NodeSub head = new NodeSub();
    private NodeSub current;


    public Queue() {
        this.head.prevNodeSub = this.head;
        this.head.nextNodeSub = this.head;
    }

    public void insert(NodeSub nodeSub) {
        if (nodeSub.nextNodeSub != null) {
            nodeSub.unlinkSub();
        }

        nodeSub.nextNodeSub = this.head.nextNodeSub;
        nodeSub.prevNodeSub = this.head;
        nodeSub.nextNodeSub.prevNodeSub = nodeSub;
        nodeSub.prevNodeSub.nextNodeSub = nodeSub;
    }

    public NodeSub popTail() {
        NodeSub nodeSub = this.head.prevNodeSub;
        if (nodeSub == this.head) {
            return null;
        } else {
            nodeSub.unlinkSub();
            return nodeSub;
        }
    }

    public NodeSub reverseGetFirst() {
        NodeSub nodeSub = this.head.prevNodeSub;
        if (nodeSub == this.head) {
            this.current = null;
            return null;
        } else {
            this.current = nodeSub.prevNodeSub;
            return nodeSub;
        }
    }

    public NodeSub reverseGetNext() {
        NodeSub nodeSub = this.current;
        if (nodeSub == this.head) {
            this.current = null;
            return null;
        } else {
            this.current = nodeSub.prevNodeSub;
            return nodeSub;
        }
    }

    public void insertBack(Node node) {
        if (node.next != null)
            node.remove();

        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        node.prev.next = node;
    }

    public void clear() {
        if (head.prev == head)
            return;
        do {
            Node node = head.prev;
            if (node == head)
                return;
            node.remove();
        } while (true);
    }

    public int getNodeCount() {
        int i = 0;

        for (NodeSub nodeSub = this.head.prevNodeSub; nodeSub != this.head; nodeSub = nodeSub.prevNodeSub) {
            ++i;
        }

        return i;
    }
}
