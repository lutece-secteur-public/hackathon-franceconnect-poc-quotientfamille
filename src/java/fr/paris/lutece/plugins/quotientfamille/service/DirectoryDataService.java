package fr.paris.lutece.plugins.quotientfamille.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

import fr.paris.lutece.plugins.crmclient.service.CRMClientService;
import fr.paris.lutece.plugins.crmclient.service.ICRMClientService;
import fr.paris.lutece.plugins.crmclient.util.CRMException;
import fr.paris.lutece.plugins.directory.business.Directory;
import fr.paris.lutece.plugins.directory.business.Entry;
import fr.paris.lutece.plugins.directory.business.Record;
import fr.paris.lutece.plugins.directory.business.RecordField;
import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.plugins.directory.service.record.IRecordService;
import fr.paris.lutece.plugins.directory.service.record.RecordService;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.service.workflow.WorkflowService;

public final class DirectoryDataService {
  
  @SuppressWarnings("deprecation")
  public static void pushInDirectory(DirectoryData directoryData, HttpServletRequest request)
  {
    
     IRecordService _recordService = SpringContextService.getBean( RecordService.BEAN_SERVICE );
    
     int _nDirectory=AppPropertiesService.getPropertyInt("directory",1);
     int _nWorkflow=AppPropertiesService.getPropertyInt("workflow",1);
     int _strNumero=AppPropertiesService.getPropertyInt("numero",1);
     int _strCivilite=AppPropertiesService.getPropertyInt("civilite",1);
     int _strNomFamille=AppPropertiesService.getPropertyInt("nomFamille",1);
     int _strUsage=AppPropertiesService.getPropertyInt("usage",1);
     int _strPrenom=AppPropertiesService.getPropertyInt("prenom",1);
     int _strEmail=AppPropertiesService.getPropertyInt("email",1);
     int _strRevenuFiscal=AppPropertiesService.getPropertyInt("revenuFiscal",1);
     int _strNombrePart=AppPropertiesService.getPropertyInt("nombrePart",1);
     int _strCrmDemande=AppPropertiesService.getPropertyInt("crmDemande",1);
     int _strCrmUser=AppPropertiesService.getPropertyInt("crmUser",1);
    
     
     
     
    Directory directory=new Directory();
    directory.setIdDirectory(_nDirectory);
    
    Record record=new Record();
    record.setDirectory(directory);
    
    
    List<RecordField> listRecordField=new ArrayList<RecordField>();
    
    Entry entryNumero=new Entry();
    entryNumero.setIdEntry(_strNumero);
    RecordField recordFieldNumero=new RecordField();
    recordFieldNumero.setEntry(entryNumero);
    recordFieldNumero.setValue(directoryData.getNumero());
    recordFieldNumero.setRecord(record);
    listRecordField.add(recordFieldNumero);
    
    Entry entryCivilite=new Entry();
    entryCivilite.setIdEntry(_strCivilite);
    RecordField recordFieldCivilite=new RecordField();
    recordFieldCivilite.setEntry(entryCivilite);
    recordFieldCivilite.setValue(directoryData.getCivilite());
    recordFieldCivilite.setRecord(record);
    listRecordField.add(recordFieldCivilite);
    
    Entry entryNomFamille=new Entry();
    entryNomFamille.setIdEntry(_strNomFamille);
    RecordField recordFieldNomFamille=new RecordField();
    recordFieldNomFamille.setEntry(entryNomFamille);
    recordFieldNomFamille.setValue(directoryData.getNomFamille());
    recordFieldNomFamille.setRecord(record);
    listRecordField.add(recordFieldNomFamille);
    
    Entry entryUsage=new Entry();
    entryUsage.setIdEntry(_strUsage);
    RecordField recordFieldUsage=new RecordField();
    recordFieldUsage.setEntry(entryUsage);
    recordFieldUsage.setValue(directoryData.getUsage());
    recordFieldUsage.setRecord(record);
    listRecordField.add(recordFieldUsage);
    
    Entry entryPrenom=new Entry();
    entryPrenom.setIdEntry(_strPrenom);
    RecordField recordFieldPrenom=new RecordField();
    recordFieldPrenom.setEntry(entryPrenom);
    recordFieldPrenom.setValue(directoryData.getPrenom());
    recordFieldPrenom.setRecord(record);
    listRecordField.add(recordFieldPrenom);
    
    Entry entryEmail=new Entry();
    entryEmail.setIdEntry(_strEmail);
    RecordField recordFieldEmail=new RecordField();
    recordFieldEmail.setEntry(entryEmail);
    recordFieldEmail.setValue(directoryData.getEmail());
    recordFieldEmail.setRecord(record);
    listRecordField.add(recordFieldEmail);
    
    Entry entryRevenuFiscal=new Entry();
    entryRevenuFiscal.setIdEntry(_strRevenuFiscal);
    RecordField recordFieldFiscal=new RecordField();
    recordFieldFiscal.setEntry(entryRevenuFiscal);
    recordFieldFiscal.setValue(directoryData.getRevenuFiscal());
    recordFieldFiscal.setRecord(record);
    listRecordField.add(recordFieldFiscal);
    
    
    Entry entryNombrePart=new Entry();
    entryNombrePart.setIdEntry(_strNombrePart);
    RecordField recordFieldNbpart=new RecordField();
    recordFieldNbpart.setEntry(entryNombrePart);
    recordFieldNbpart.setValue(directoryData.getNombrePart());
    recordFieldNbpart.setRecord(record);
    listRecordField.add(recordFieldNbpart);
    
    ICRMClientService _crmClientService = SpringContextService.getBean( CRMClientService.BEAN_SERVICE );
    String _strIdDemandeType=AppPropertiesService.getProperty("idDemandeType","2");
    try {
      LuteceUser user = SecurityService.getInstance().getRemoteUser(request);
      String strCrmDemande = _crmClientService.sendCreateDemandByUserGuid(_strIdDemandeType,
          user.getName(), "NOUVEAU", "En attente de traitement", "");
      Entry entryCrmDemande=new Entry();
      entryCrmDemande.setIdEntry(_strCrmDemande);
      RecordField recordFieldCrmDemande=new RecordField();
      recordFieldCrmDemande.setEntry(entryCrmDemande);
      recordFieldCrmDemande.setValue(strCrmDemande);
      recordFieldCrmDemande.setRecord(record);
      listRecordField.add(recordFieldCrmDemande);

      Entry entryCrmUser=new Entry();
      entryCrmUser.setIdEntry(_strCrmUser);
      RecordField recordFieldCrmUser=new RecordField();
      recordFieldCrmUser.setEntry(entryCrmUser);
      recordFieldCrmUser.setValue(user.getName());
      recordFieldCrmUser.setRecord(record);
      listRecordField.add(recordFieldCrmUser);

    } catch (CRMException | UserNotSignedException e) {
      // TODO Auto-generated catch block
      AppLogService.error( "Error DirectoryDataService " + e);
      e.printStackTrace();
    }

    record.setListRecordField( listRecordField );
    
    _recordService.create(record, PluginService.getPlugin(DirectoryPlugin.PLUGIN_NAME));
    
    
    WorkflowService.getInstance(  ).getState( record.getIdRecord(), Record.WORKFLOW_RESOURCE_TYPE, _nWorkflow, _nDirectory );
    WorkflowService.getInstance(  ).executeActionAutomatic( record.getIdRecord(), Record.WORKFLOW_RESOURCE_TYPE, _nWorkflow, _nDirectory  );
    
    
    
    
    
    
    
  }

}
