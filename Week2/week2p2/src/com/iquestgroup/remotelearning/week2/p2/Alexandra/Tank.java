package com.iquestgroup.remotelearning.week2.p2.Alexandra;


public class Tank {
    private int nrElements;
    private final String ADD = "Element added to tank";
    private final String REMOVE = "Element removed from tank";
    private final String EMPTY = "Tank empty";
    private final String APPROVEREMOVE = "Tank can be removed";
    private final String DISSAPROVEREMOVE = "Not to be removed, not empty";

    public Tank() {
        this.nrElements = 0;
    }

    public void pushTank() {
        nrElements++;
        System.out.println(ADD);
    }

    public void popTank() {
        if (nrElements > 0) {
            nrElements--;
            System.out.println(REMOVE);
        } else {
            System.out.println(EMPTY);
        }
    }


    public int getNrElements() {
        return nrElements;
    }

    @Override
    protected void finalize() {
        if (nrElements == 0) {
            System.out.println(APPROVEREMOVE);
        } else {
            System.out.println(DISSAPROVEREMOVE);
        }
    }
}
