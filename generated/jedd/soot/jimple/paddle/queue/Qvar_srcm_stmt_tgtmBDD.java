package soot.jimple.paddle.queue;

import soot.util.*;
import soot.jimple.paddle.bdddomains.*;
import soot.jimple.paddle.*;
import soot.jimple.toolkits.callgraph.*;
import soot.*;
import soot.util.queue.*;
import jedd.*;
import java.util.*;

public final class Qvar_srcm_stmt_tgtmBDD extends Qvar_srcm_stmt_tgtm {
    public Qvar_srcm_stmt_tgtmBDD(String name) { super(name); }
    
    private LinkedList readers = new LinkedList();
    
    public void add(VarNode _var, SootMethod _srcm, Unit _stmt, SootMethod _tgtm) {
        this.add(new jedd.internal.RelationContainer(new Attribute[] { var.v(), srcm.v(), stmt.v(), tgtm.v() },
                                                     new PhysicalDomain[] { V1.v(), T1.v(), ST.v(), T2.v() },
                                                     ("this.add(jedd.internal.Jedd.v().literal(new java.lang.Object" +
                                                      "[...], new jedd.Attribute[...], new jedd.PhysicalDomain[...]" +
                                                      ")) at /home/olhotak/soot-ref/src/soot/jimple/paddle/queue/Qv" +
                                                      "ar_srcm_stmt_tgtmBDD.jedd:34,8-11"),
                                                     jedd.internal.Jedd.v().literal(new Object[] { _var, _srcm, _stmt, _tgtm },
                                                                                    new Attribute[] { var.v(), srcm.v(), stmt.v(), tgtm.v() },
                                                                                    new PhysicalDomain[] { V1.v(), T1.v(), ST.v(), T2.v() })));
    }
    
    public void add(final jedd.internal.RelationContainer in) {
        for (Iterator it = readers.iterator(); it.hasNext(); ) {
            Rvar_srcm_stmt_tgtmBDD reader = (Rvar_srcm_stmt_tgtmBDD) it.next();
            reader.add(new jedd.internal.RelationContainer(new Attribute[] { srcm.v(), tgtm.v(), var.v(), stmt.v() },
                                                           new PhysicalDomain[] { T1.v(), T2.v(), V1.v(), ST.v() },
                                                           ("reader.add(in) at /home/olhotak/soot-ref/src/soot/jimple/pad" +
                                                            "dle/queue/Qvar_srcm_stmt_tgtmBDD.jedd:39,12-18"),
                                                           in));
        }
    }
    
    public Rvar_srcm_stmt_tgtm reader(String rname) {
        Rvar_srcm_stmt_tgtm ret = new Rvar_srcm_stmt_tgtmBDD(name + ":" + rname);
        readers.add(ret);
        return ret;
    }
}