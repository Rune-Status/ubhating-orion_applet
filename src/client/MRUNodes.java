package client;


public final class MRUNodes {

   private final NodeSub emptyNodeSub = new NodeSub();
   private final int initialCount;
   private int spaceLeft;
   private final NodeCache nodeCache;
   private final Queue requests = new Queue();


   public MRUNodes(int i) {
      this.initialCount = i;
      this.spaceLeft = i;
      this.nodeCache = new NodeCache();
   }

   public NodeSub insertFromCache(long l) {
      NodeSub nodeSub = (NodeSub)this.nodeCache.findNodeByID(l);
      if(nodeSub != null) {
         this.requests.insert(nodeSub);
      }

      return nodeSub;
   }

   public void removeFromCache(NodeSub nodeSub, long l) {
      try {
         if(this.spaceLeft == 0) {
            NodeSub runtimeexception = this.requests.popTail();
            runtimeexception.remove();
            runtimeexception.unlinkSub();
            if(runtimeexception == this.emptyNodeSub) {
               NodeSub nodeSub_2 = this.requests.popTail();
               nodeSub_2.remove();
               nodeSub_2.unlinkSub();
            }
         } else {
            --this.spaceLeft;
         }

         this.nodeCache.removeFromCache(nodeSub, l);
         this.requests.insert(nodeSub);
      } catch (RuntimeException var6) {
         SignLink.reporterror("47547, " + nodeSub + ", " + l + ", " + 2 + ", " + var6.toString());
         throw new RuntimeException();
      }
   }

   public void unlinkAll() {
      while(true) {
         NodeSub nodeSub = this.requests.popTail();
         if(nodeSub == null) {
            this.spaceLeft = this.initialCount;
            return;
         }

         nodeSub.remove();
         nodeSub.unlinkSub();
      }
   }
}
