// /////////////////////////////////////////////////////////////////////////////
// REFCODES.ORG
// /////////////////////////////////////////////////////////////////////////////
// This code is written and provided by Siegfried Steiner, Munich, Germany.
// Feel free to use it as skeleton for your own applications. Make sure you have
// considered the license conditions of the included artifacts (pom.xml).
// -----------------------------------------------------------------------------
// The REFCODES.ORG artifacts used by this template are copyright (c) by
// Siegfried Steiner, Munich, Germany and licensed under the following
// (see "http://en.wikipedia.org/wiki/Multi-licensing") licenses:
// -----------------------------------------------------------------------------
// GNU General Public License, v3.0 ("http://www.gnu.org/licenses/gpl-3.0.html")
// -----------------------------------------------------------------------------
// Apache License, v2.0 ("http://www.apache.org/licenses/LICENSE-2.0")
// -----------------------------------------------------------------------------
// Please contact the copyright holding author(s) of the software artifacts in
// question for licensing issues not being covered by the above listed licenses,
// also regarding commercial licensing models or regarding the compatibility
// with other open source licenses.
// /////////////////////////////////////////////////////////////////////////////

package task1;

import static org.refcodes.console.ConsoleSugar.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.refcodes.configuration.ext.runtime.RuntimeProperties;
import org.refcodes.configuration.ext.runtime.RuntimePropertiesImpl;
import org.refcodes.console.Condition;
import org.refcodes.console.Flag;
import org.refcodes.console.StringOption;
import org.refcodes.console.SyntaxNotation;
import org.refcodes.data.AsciiColorPalette;
import org.refcodes.logger.RuntimeLogger;
import org.refcodes.logger.RuntimeLoggerFactorySingleton;
import org.refcodes.runtime.SystemUtility;
import org.refcodes.textual.FontImpl;
import org.refcodes.textual.FontStyle;
import org.refcodes.textual.FontType;

/**
 * A minimum REFCODES.ORG enabled command line interface (CLI) application. Get
 * inspired by "https://bitbucket.org/funcodez".
 */
public class Main {

	// See "http://www.refcodes.org/blog/logging_like_the_nerds_log" |-->
	private static RuntimeLogger LOGGER = RuntimeLoggerFactorySingleton.createRuntimeLogger();
	// <--| See "http://www.refcodes.org/blog/logging_like_the_nerds_log"

	// /////////////////////////////////////////////////////////////////////////
	// STATICS:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// CONSTANTS:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// VARIABLES:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// INJECTION:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// METHODS:
	// /////////////////////////////////////////////////////////////////////////

	public static void main( String args[] ) throws SecurityException, UnsupportedEncodingException {

		// See "http://www.refcodes.org/refcodes/refcodes-console" |-->
		StringOption theEchoOption = stringOption( "-e", "--echo", "echo", "Echoes the provided message to the standard out stream." );
		Flag theVerboseFlag = verboseFlag();
		Flag theSysInfoFlag = sysInfoFlag();
		Flag theHelpFlag = helpFlag();

		// @formatter:off
		Condition theRoot =  xor(
			and( 
				theEchoOption, optional( theVerboseFlag ) 
			),
			xor(
				theHelpFlag, theSysInfoFlag
			)
		);
		// @formatter:on
		// <--| See "http://www.refcodes.org/refcodes/refcodes-console"

		// See "http://www.refcodes.org/refcodes/refcodes-console" |-->
		RuntimeProperties theProperties = new RuntimePropertiesImpl( theRoot );
		theProperties.withSyntaxNotation( SyntaxNotation.GNU_POSIX );
		theProperties.withName( "task1" ).withTitle( ">>> " + "task1".toUpperCase() + " >>>" ).withCopyrightNote( "Copyright (c) by task1" ).withLicenseNote( "Licensed under GNU General Public License, v3.0 and Apache License, v2.0" );
		theProperties.withBannerFont( new FontImpl( FontType.DIALOG, FontStyle.BOLD ) ).withBannerFontPalette( AsciiColorPalette.MAX_LEVEL_GRAY.getPalette() );
		theProperties.setDescription( "A minimum REFCODES.ORG enabled command line interface (CLI) application. Get inspired by [https://bitbucket.org/funcodez]." );
		try {
			theProperties.evalArgs( args );
			if ( theHelpFlag.getValue() ) {
				theProperties.printHelp();
				System.exit( 0 );
			}
			if ( theSysInfoFlag.getValue() ) {
				theProperties.printBanner();
				System.out.println( SystemUtility.toPrettySystemInfo() );
				System.exit( 0 );
			}
		}
		catch ( Exception e ) {
			theProperties.printHelp();
			theProperties.errorLn( e.getMessage() );
			System.exit( 1 );
		}
		// <--| See "http://www.refcodes.org/refcodes/refcodes-console"

		try {
			if ( theProperties.getBoolean( "verbose" ) ) {
				theProperties.printBanner();
			}
			try {
				// Put an "*.ini" file as sepcified in the documentation |--> 
				theProperties.withFilePath( "task1.ini" );
				// <--| Put an "*.ini" file as sepcified in the documentation 
			}
			catch ( IOException ignore ) {
				// Continue in case there is none sich "*.ini" file |-->
				if ( theProperties.getBoolean( "verbose" ) ) {
					LOGGER.warn( "No config file with name <task1.ini> found, skipping." );
				}
				// <--| Continue in case there is none sich "*.ini" file
			}
			if ( theProperties.getBoolean( "verbose" ) ) {
				LOGGER.info( "Starting application <task1>..." );
				LOGGER.info( "Name: \"" + theProperties.get( "application/name" ) + "\"" );
				LOGGER.info( "Company: \"" + theProperties.get( "application/company" ) + "\"" );
				LOGGER.info( "Version: \"" + theProperties.get( "application/version" ) + "\"" );
				LOGGER.info( "Echo: \"" + theProperties.get( "echo" ) + "\"" );
				LOGGER.info( "Done." );
			}
			else {
				System.out.println( theProperties.get( "echo" ) );
			}

		}
		catch ( Exception e ) {
			// theArgsParser.printBody();
			LOGGER.error( e.getMessage(), e );
			System.exit( 1 );
		}
	}

	// /////////////////////////////////////////////////////////////////////////
	// HOOKS:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// HELPER:
	// /////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// INNER CLASSES:
	// /////////////////////////////////////////////////////////////////////////

}