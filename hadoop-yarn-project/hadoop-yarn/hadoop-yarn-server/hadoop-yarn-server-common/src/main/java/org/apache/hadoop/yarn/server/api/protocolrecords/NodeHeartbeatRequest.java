/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.server.api.protocolrecords;

import java.util.Map;
import java.util.Set;

import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.server.api.records.MasterKey;
import org.apache.hadoop.yarn.server.api.records.NodeStatus;
import org.apache.hadoop.yarn.util.Records;

public abstract class NodeHeartbeatRequest {
  
  public static NodeHeartbeatRequest newInstance(NodeStatus nodeStatus,
      MasterKey lastKnownContainerTokenMasterKey,
      MasterKey lastKnownNMTokenMasterKey, Set<String> nodeLabels) {
    NodeHeartbeatRequest nodeHeartbeatRequest =
        Records.newRecord(NodeHeartbeatRequest.class);
    nodeHeartbeatRequest.setNodeStatus(nodeStatus);
    nodeHeartbeatRequest
        .setLastKnownContainerTokenMasterKey(lastKnownContainerTokenMasterKey);
    nodeHeartbeatRequest
        .setLastKnownNMTokenMasterKey(lastKnownNMTokenMasterKey);
    nodeHeartbeatRequest.setNodeLabels(nodeLabels);
    return nodeHeartbeatRequest;
  }

  public static NodeHeartbeatRequest newInstance(NodeStatus nodeStatus,
      MasterKey lastKnownContainerTokenMasterKey,
      MasterKey lastKnownNMTokenMasterKey, Set<String> nodeLabels,
      Map<ApplicationId, String> registeredCollectors) {
    NodeHeartbeatRequest nodeHeartbeatRequest =
        NodeHeartbeatRequest.newInstance(nodeStatus,
            lastKnownContainerTokenMasterKey,
            lastKnownNMTokenMasterKey,
            nodeLabels);
    nodeHeartbeatRequest.setRegisteredCollectors(registeredCollectors);
    return nodeHeartbeatRequest;
  }

  public abstract NodeStatus getNodeStatus();
  public abstract void setNodeStatus(NodeStatus status);

  public abstract MasterKey getLastKnownContainerTokenMasterKey();
  public abstract void setLastKnownContainerTokenMasterKey(MasterKey secretKey);
  
  public abstract MasterKey getLastKnownNMTokenMasterKey();
  public abstract void setLastKnownNMTokenMasterKey(MasterKey secretKey);
  
  public abstract Set<String> getNodeLabels();
  public abstract void setNodeLabels(Set<String> nodeLabels);

  // This tells RM registered collectors' address info on this node
  public abstract Map<ApplicationId, String> getRegisteredCollectors();
  public abstract void setRegisteredCollectors(Map<ApplicationId,
      String> appCollectorsMap);
}
