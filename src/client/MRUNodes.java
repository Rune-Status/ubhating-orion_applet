package client;


public final class MRUNodes {

   private final NodeSub emptyNodeSub = new NodeSub();
   private final int initialCount;
   private int spaceLeft;
   private final NodeCache nodeCache;
   private final LinkedList linkedList = new LinkedList();


   public MRUNodes(int i) {
      this.initialCount = i;
      this.spaceLeft = i;
      this.nodeCache = new NodeCache();
   }

   public NodeSub insertFromCache(long l) {
      NodeSub nodeSub = (NodeSub)this.nodeCache.findNodeByID(l);
      if(nodeSub != null) {
         this.linkedList.insertHead(nodeSub);
      }

      return nodeSub;
   }

   public void removeFromCache(NodeSub nodeSub, long l) {
      try {
         if(this.spaceLeft == 0) {
            NodeSub runtimeexception = this.linkedList.popTail();
            runtimeexception.unlink();
            runtimeexception.unlinkSub();
            if(runtimeexception == this.emptyNodeSub) {
               NodeSub nodeSub_2 = this.linkedList.popTail();
               nodeSub_2.unlink();
               nodeSub_2.unlinkSub();
            }
         } else {
            --this.spaceLeft;
         }

         this.nodeCache.removeFromCache(nodeSub, l);
         this.linkedList.insertHead(nodeSub);
      } catch (RuntimeException var6) {
         SignLink.reporterror("47547, " + nodeSub + ", " + l + ", " + 2 + ", " + var6.toString());
         throw new RuntimeException();
      }
   }

   public void unlinkAll() {
      while(true) {
         NodeSub nodeSub = this.linkedList.popTail();
         if(nodeSub == null) {
            this.spaceLeft = this.initialCount;
            return;
         }

         nodeSub.unlink();
         nodeSub.unlinkSub();
      }
   }
}
