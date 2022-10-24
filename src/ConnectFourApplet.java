

import java.awt.*;
import java.applet.*;

@SuppressWarnings("serial")
public class ConnectFourApplet extends Applet {	

	private static MinMaxPlayer computer = new MinMaxPlayer();
	Board board = new Board();
	
	final int cellSize = 50;
	final int xStart = 30;	            //column start coordinate
	final int yStart = 70;               //row start coordinate
	final int yEnd = yStart + (cellSize * board.rowCount); 
	final int xEnd = xStart + (cellSize * board.columnCount); 

	//constant coordinates for turn/winner color rectangle
	final int xColorBox = xStart;
	final int yColorBox = yEnd + 50;

	final int greenTurn = 1; 
	final int redTurn = 2;   
	final int boardFull = 3; 

	public void init()
	{
		setSize(500,500);	
		setVisible(true);
		setBackground(Color.white);
	}

	public void paint(Graphics g)
	{
		//displays title over board
		g.setFont(new Font ("TimesRoman", Font.BOLD, 18));
		g.setColor(Color.blue);
		g.drawString("Connect Four Game", 25, 50);

		for(int y = yStart; y < yEnd; y += cellSize)     //y controls rows
		{	
			for(int x = xStart; x < xEnd; x += cellSize)    //x controls columns
			{
				//draws board
				g.setColor(Color.black);
				g.drawRect(x, y, cellSize, cellSize);

				switch(board.getCell((y - yStart) / cellSize, (x - xStart) / cellSize))
				{
					case 1:	
						
						g.setColor(Color.red); 
						g.fillOval(x, y, cellSize, cellSize);
						break;
	
					case 2:	
						
						g.setColor(Color.green); 
						g.fillOval(x, y, cellSize, cellSize);
						break;
				}
			}
		}

		//decides red/green turn/winner and changes color box appropriately
		if(board.isOver()) {
			
			switch(board.mWinner)
			{
			
				case 1:	
					
					g.setColor(Color.red);
					g.fillRect(xColorBox, yColorBox, 140, 30);
					g.setColor(Color.black);
					g.drawString("Red won!" , xColorBox + 10, yColorBox + 20);
					break;	  
		
				case 2:	 
					
					g.setColor(Color.green);
					g.fillRect(xColorBox, yColorBox, 140, 30);
					g.setColor(Color.black);
					g.drawString("Green won!", xColorBox + 10, yColorBox + 20);
					break;		  
			
				case 0:	
					
					g.setColor(Color.yellow);
					g.fillRect(xColorBox, yColorBox, 140, 30);
					g.setColor(Color.black);
					g.drawString("Board Full!", xColorBox + 10, yColorBox + 20);
					break;
			}
		}
		else {

			switch(board.mNextPlayer)
			{
				case 2:	
					
					g.setColor(Color.green);
					g.fillRect(xColorBox, yColorBox, 140, 30);
					g.setColor(Color.black);
					g.drawString("Green's Turn", xColorBox + 10, yColorBox + 20);
					break;
	
				case 1:	
					
					g.setColor(Color.red);
					g.fillRect(xColorBox, yColorBox, 140, 30);
					g.setColor(Color.black);
					g.drawString("Red's Turn", xColorBox+10, yColorBox+20);
					break;		 
			}
		}
	}//end of paint method


	public boolean handleEvent(Event e)
	{ 
		switch (e.id)
		{
			case Event.MOUSE_DOWN:	
	
				putPiece(e.x, e.y);
		}
		return true;
	}

	public void frepaint()
	{
		Graphics g = getGraphics();
		update(g);
	}

	public void putPiece(int x, int y)
	{
		if (!board.isOver() && (xStart < x && x < xEnd) && (yStart < y && y < yEnd))
		{
			int column = (x - xStart) / cellSize;

			boolean valid = board.Move(column);
			
			if(valid) {

				frepaint();
			
				if(!board.isOver()) {
					
					computer.play(board);
				}
			}
			frepaint();
		}
	}
}
