package org.fogbeam.example.opennlp;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

/**
 * Tokenizer Test
 * @author Alex Jimmy Montaño Fuentes
 */
public class TokenizerTest {
	
	static TokenizerModel model;
	static Tokenizer tokenizer;
	
	/**
	 * Initialize model and tokenizer for tests
	 * @throws InvalidFormatException TokenizerModel constructor may cause InvalidFormatException
	 * @throws IOException TokenizerModel constructor may cause IOException
	 */
	@BeforeClass
	public static void initializeTest() throws InvalidFormatException, IOException {
		model = new TokenizerModel(new FileInputStream("models/en-token.model"));
		tokenizer = new TokenizerME(model);
	}

	/**
	 * Test to check tokens of "Hello test!"
	 */
	@Test
	public void test() {
		String[] tokens = tokenizer.tokenize("Hello test!");
		
		assertEquals(3, tokens.length);
		assertEquals("Hello", tokens[0]);
		assertEquals("test", tokens[1]);
		assertEquals("!", tokens[2]);
	}

}
