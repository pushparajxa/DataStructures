package com.ds.graph.utils;

public class CommonGraphUtils {
    public static final String BRIDGE_FLAG = "BRIDGE";
    public  static final String VISITED_FLAG = "VISITED_FLAG";
    public  static final String EDGE_TYPE = "EDGE_TYPE";
    public static final String START_TIME="StartTime";
    public static final String END_TIME ="EndTime";
    public static final String PARENT = "Parent";
    public static final String LOW_TIME = "LowTime";
    public static final String DISC_TIME = "DiscoveryTime";
    public static final String ARTICULATION_POINT_FLAG = "ArticulationPointFlag";
    public static final String ON_STACK_FLAG = "OnStack";
    public  static  final String IN_DEGREE_COUNT="InDegreeCountOfAVertex";
    public static final String TOPOLOGICAL_ORDER_COUNT = "TopologicalOrderCount";
    public static final String TOPO_VISIT_STATUS =
        "TopologicalOrderVisitStatusUsedInDFS";
    public  enum  TOPOLOGICAL_ORDER_VISIT_STATUS {TEMPORARY, PERMANENT };
}
