package th.co.aerothai.document.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNUtils {
	
	public SVNUtils(){
		DAVRepositoryFactory.setup();
	}
//	public static void uploadFile(File file, String fileName) {
//		try {		
//			SVNURL destinURL = SVNURL.parseURIEncoded(PropertiesUtils.getSVNProperties().getString("SVNTargetURL"));
//			SVNURL fileURL = destinURL.appendPath(file.getName(), false);
//			SVNCommitInfo svninfo = importDirectory(file, fileURL,"committing file", false);
//			System.out.println(svninfo.toString());
//		} catch (SVNException e) {
//			// TODO: handle exception
//		}
//	}
//	
//	public static void downloadFile(){
//		
//	}
//
//	private static SVNCommitInfo importDirectory(File localPath, SVNURL dstURL, String commitMessage, boolean isRecursive) throws SVNException {
//		SVNClientManager ourClientManager = SVNUtils.getSVNClientManager();
//		return ourClientManager.getCommitClient().doImport(localPath, dstURL,commitMessage, isRecursive);
//	}
//
//	private static SVNClientManager getSVNClientManager() {
//		ISVNAuthenticationManager authManager = null;	
//		authManager = SVNWCUtil.createDefaultAuthenticationManager(PropertiesUtils.getSVNProperties().getString("SVNUName"), PropertiesUtils.getSVNProperties().getString("SVNPwd"));
//		SVNClientManager manager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true), authManager);
//		return manager;
//	}
	
//	private static UserSession getUserSession(){
//		UserSession userSession = (UserSession) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constants.AUTH_KEY);
//		
//		return userSession;
//	}
	
	private static SVNRepository getRepository() throws SVNException{
		SVNURL url = SVNURL.parseURIDecoded(PropertiesUtils.getSVNProperties().getString("SVNTargetURL"));
//		ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(getUserSession().getUsername() ,getUserSession().getPassword());
		ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(PropertiesUtils.getSVNProperties().getString("SVNUser") ,PropertiesUtils.getSVNProperties().getString("SVNPass"));
		SVNRepository repository = SVNRepositoryFactory.create(url);
		
		repository.setAuthenticationManager(authenticationManager);
		
		return repository;
	}
	
	public static boolean uploadFile(InputStream stream, String fileName){		
		try{
			SVNRepository repository = getRepository();	
			ISVNEditor editor = repository.getCommitEditor("file added", null);
			byte[] data = IOUtils.toByteArray(stream);
			SVNCommitInfo info = addDir(editor, null, fileName, data);
			return true;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} catch (SVNException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public static ByteArrayInputStream downloadFile(String fileName) throws SVNException{
		String filePath = PropertiesUtils.getSVNProperties().getString("SVNDocDir") + fileName;
		SVNRepository repository = getRepository();
		Map fileProperties = new HashMap();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		repository.getFile(filePath, -1, SVNProperties.wrap(fileProperties), outputStream);
		IOUtils.closeQuietly(outputStream);
		
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	public static void deleteFile(String fileName) throws SVNException{
		SVNRepository repository = getRepository();
		ISVNEditor editor = repository.getCommitEditor("file deleted", null);
		SVNCommitInfo info = deleteDir(editor, fileName);
		System.out.println(info.toString());
	}
	
	private static SVNCommitInfo addDir(ISVNEditor editor, String dirPath, String filePath, byte[] data) throws SVNException {
		
        editor.openRoot(-1);
        //editor.addDir(dirPath, null, -1);     
        editor.openDir(PropertiesUtils.getSVNProperties().getString("SVNDocDir"), -1);      
        editor.addFile(filePath, null, -1);
        editor.applyTextDelta(filePath, null);

        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
        String checksum = deltaGenerator.sendDelta(filePath, new ByteArrayInputStream(data), editor, true);

        editor.closeFile(filePath, checksum);

        //Closes dirPath.
        editor.closeDir();

        //Closes the root directory.
        editor.closeDir();

        return editor.closeEdit();
    }
	
	 private static SVNCommitInfo deleteDir( ISVNEditor editor , String dirPath ) throws SVNException {
	        editor.openRoot( -1 );
	        editor.openDir(PropertiesUtils.getSVNProperties().getString("SVNDocDir"), -1); 
	        editor.deleteEntry( dirPath , -1 );

	        //Closes dirPath.
	        editor.closeDir();
	        
	        //Closes the root directory.
	        editor.closeDir( );

	        return editor.closeEdit( );
	    }
	
	public static void main(String[] args) throws SVNException, IOException {
		
//		DAVRepositoryFactory.setup();
//		SVNURL url = SVNURL.parseURIDecoded(PropertiesUtils.getSVNProperties().getString("SVNTargetURL"));
//		ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(PropertiesUtils.getSVNProperties().getString("SVNUName") ,PropertiesUtils.getSVNProperties().getString("SVNPwd"));
//		SVNRepository repository = SVNRepositoryFactory.create(url);
//		repository.setAuthenticationManager(authenticationManager);
//		ISVNEditor editor = repository.getCommitEditor("file added", null);
//		InputStream stream = IOUtils.toInputStream("fasfsfjsfsgiosefsiefjsefe");
//		byte[] data = IOUtils.toByteArray(stream);
//		SVNCommitInfo info = addDir(editor, null, "test/text.txt", data);
//		System.out.println(info.toString());
		
//		  try {
//		SVNURL destinURL = SVNURL.parseURIEncoded(PropertiesUtils.getSVNProperties().getString("SVNTargetURL"));
//		SVNURL fileURL = destinURL.appendPath("text.txt", false);
//		SVNUpdateClient updateClient = SVNUtils.getSVNClientManager().getUpdateClient();
//		File file = new File(Constants.UPLOAD_LOCATION+"text.txt");
//	
//	    updateClient.setIgnoreExternals(false);
//	      
//	      
//		//updateClient.doCheckout(destinURL , file , SVNRevision.UNDEFINED , SVNRevision.UNDEFINED , false );
//	    updateClient.doExport(fileURL, file, SVNRevision.UNDEFINED, SVNRevision.UNDEFINED, "end", false, false);
//			} catch (SVNException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
	
}
