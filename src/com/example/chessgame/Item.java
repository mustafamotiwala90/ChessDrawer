package com.example.chessgame;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items")
	public class Item extends Model {
		@Column(name = "Number")
		public int number;
		@Column(name = "Move")
		public String move;

	        public Item(){
	                super();
	        }
	        public Item(int number, String move){
	                super();
	                this.number = number;
	                this.move = move;
	        }
	}
	
	