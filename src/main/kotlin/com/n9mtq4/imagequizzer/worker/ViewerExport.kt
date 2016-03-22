package com.n9mtq4.imagequizzer.worker

import com.n9mtq4.kotlin.extlib.io.open
import com.n9mtq4.kotlin.extlib.pstAndGiven
import com.n9mtq4.kotlin.extlib.pstAndNull
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files

/**
 * Created by will on 3/17/16 at 10:19 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */

/**
 * Copies all files listed in FILES_TO_EXPORT to a given directory
 * */
internal fun exportViewerToDirectory(dir: File) {
	
	readFromJar("export.txt").lines().forEach {
		pstAndNull { // for every thing in FILES_TO_EXPORT and printStackTrace if error
			val jarName = "viewer/$it" // name in jar
			val text = readFromJar(jarName) // read the text from it
			open(File(dir, it), "w") // write it out to the new file
					.write(text)
					.close()
		}
	}
	
}

/**
 * Reads the text from a file inside this jar
 * */
private fun readFromJar(path: String): String = pstAndGiven("Error when exporting files!") {
	
	val input = Files::class.java.getResourceAsStream(path)
	val ir = InputStreamReader(input)
	val br = BufferedReader(ir)
	
	br.readLines().joinToString(separator = "\n")
	
}