import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    // 1. Fetch XML String stored in property
    def xmlString = message.getProperty("PreResponse") as String;
    
    if (xmlString) {
        // 2. Parse XML
        def xml = new XmlSlurper().parseText(xmlString);
        
        // 3. Extract values from XML tags
        def executionId = xml.StatementName.storedProcedureName.ExecutionId.text();
        def processLogId = xml.StatementName.storedProcedureName.ProcessLogId.text();
        def loadKey = xml.StatementName.storedProcedureName.LoadKey.text();
        
        // 4. Set extracted values into new properties (or headers)
        message.setProperty("ExecutionId", executionId);
        message.setProperty("ProcessLogId", processLogId);
        message.setProperty("LoadKey", loadKey);
    }
    
    return message;
}