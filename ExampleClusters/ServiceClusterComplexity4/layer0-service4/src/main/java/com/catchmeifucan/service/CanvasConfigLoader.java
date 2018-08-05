/**
 * MIT License
 Copyright (c) 2017 deepakshajan
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package com.catchmeifucan.service;

import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to load all the configurations from the file.
 * @author Deepak Shajan
 */
final class CanvasConfigLoader {

	/**
	 * To load all the configurations from all the files
	 * @return Object loaded with the entire configurations
	 */
	ConfigurationEntity loadFullConfigurations() {

		ConfigurationEntity config;
		config = getPartiallyLoadedConfigEntity();
		config.setCanvasLayout(getCanvasLayoutMatrix());
		return config
	}

	/**
	 * Loads all the configurations defined in <code>config.json</code> file
	 * @see <text>config.json</text>
	 * @return configuration object loaded with configurations from <code>config.json</code>
	 */
	private ConfigurationEntity getPartiallyLoadedConfigEntity() {
		
		StringBuilder builder = new StringBuilder();
		String line;
		try(BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource("./static/config/config.json").getFile()))) {
			while((line = br.readLine())!=null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Gson().fromJson(builder.toString(), ConfigurationEntity.class);
	}

	/**
	 * Loads the configurations related to the cavas layout.
	 * <p>The layout configurtation is defined in the file <code>canvasLayout.txt</code></p>
	 * @return a matrix which is the numerical representation of the canvas layout.
     * @see <text>canvasLayout.txt</text> file
	 */
	private String[][] getCanvasLayoutMatrix() {
		
		int CANVAS_DIV_X = 25;
		int CANVAS_DIV_Y = 25;

		String[][] layout = new String[CANVAS_DIV_X][CANVAS_DIV_Y];
		
		try(BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource("./static/config/canvasLayout.txt").getFile()))) {
			String line;
			int i=0,j;
			while((line = br.readLine())!=null) {
				j=0;
				for(char c : line.toCharArray()) {
					if(c!=' ') {
						layout[i][j++]=c+"";
					}
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return layout;
	}

}
