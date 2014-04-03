package com.github.rocon_rosjava_core.master_info;

/*****************************************************************************
** Imports
*****************************************************************************/

import org.apache.commons.logging.Log;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.exception.RosRuntimeException;

import com.github.rocon_rosjava_core.rosjava_utils.ListenerNode;

/*****************************************************************************
** MasterInfo
*****************************************************************************/

public class MasterInfo extends AbstractNodeMain {

	private rocon_std_msgs.MasterInfo msg;

    /**
     * Go get the master information.
     *
     * @param connectedNode
     */
    @Override
    public void onStart(final ConnectedNode connectedNode) {
        final Log log = connectedNode.getLog();
    	ListenerNode<rocon_std_msgs.MasterInfo> masterInfo = new ListenerNode<rocon_std_msgs.MasterInfo>(connectedNode, "/concert/info", "rocon_std_msgs/MasterInfo");
    	try {
    		masterInfo.waitForResponse();
        	this.msg = masterInfo.getMessage();
        	log.info("Master Info : retrieved successfully [" + this.msg.getName() + "]");
    	} catch (RosRuntimeException e) {
    		log.error("Master Info : " + e.getMessage());
    	}
    }

    /****************************************
    ** Getters
    ****************************************/

    @Override
    public GraphName getDefaultNodeName() {
		return GraphName.of("rocon/master_info");
    }
    
    public String getName() {
    	return this.msg.getName();
    }

    public String getDescription() {
    	return this.msg.getDescription();
    }

    public String getIconResourceName() {
    	return this.msg.getIcon().getResourceName();
    }

    public String getIconFormat() {
    	return this.msg.getIcon().getFormat();
    }
    
//    public Icon getIcon() {
//    	// dunno yet
//    }

    /****************************************
    ** Main
    ****************************************/

   public static void main(String argv[]) throws java.io.IOException {
    	String[] args = { "com.github.rocon_rosjava_core.master_info.MasterInfo" };
    	try {
    		org.ros.RosRun.main(args);
    	} catch(RosRuntimeException e) {
    		System.out.println("Master Info: ros runtime error");
		} catch(Exception e) {
			System.out.println("Master Info: unknown error");
    	}
    }    	
}


