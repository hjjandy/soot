package ca.mcgill.sable.soot;

import java.util.*;
import ca.mcgill.sable.soot.*;
import java.io.*;


public class CompleteBlockGraph extends BlockGraph 
{

    CompleteBlockGraph(UnitBody body)
    {
	super(body, COMPLETE);
    }
}


