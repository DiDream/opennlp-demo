
package org.fogbeam.example.opennlp;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Main class of the application
 */
public class TokenizerMain
{
	/**
	 * The relative route to the project of the data files
	 * 
	 * @author Alex Jimmy Montaño Fuentes
	 */
	static String dataPath = "eval_data";
	
	/**
	 * Main method of the application
	 * @param args Invocation arguments
	 * @throws Exception
	 */
	public static void main( String[] args ) throws Exception
	{
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
			
			Tokenizer tokenizer = new TokenizerME(model);
			
			File folder = new File(dataPath);
			
			List<String[]> fileTokens = new ArrayList<String[]>();
			
			
			
			for (final File fileEntry : folder.listFiles()) {
				if (!fileEntry.isDirectory()) {
					String fileName = fileEntry.getName();
					fileTokens.add( tokenizer.tokenize(new String(Files.readAllBytes(Paths.get(dataPath, fileName)))) );
				}
		    }
			
			for( String[] tokens : fileTokens )
			{
				System.out.println( "---- BEGIN FILE ----" );
				
				for( String token : tokens )
				{
					System.out.println( token );
				}
				
				System.out.println( "---- END FILE ----" );
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
