# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:43:59 2021

@author: 2rome
"""
from matplotlib import pyplot as plt

class TSP:
    def __init__(self):
        self.dimension = 0
        self.problema = {}
        self.solution = []
        self.figures = 0
        
        

    def obtener_desde_archivo_tsp(self, tsp_name):
        tsp_file = f"./TSP_interesantes/{tsp_name}"
        lines = self.read_file(tsp_file)
        self.dimension = int([line.partition('DIMENSION:')[2] for line in lines if 'DIMENSION: ' in line][0])
        index_for_search = [index for index,line in enumerate(lines) if 'NODE_COORD_SECTION' in line][0]  +1
        cities_data = lines[index_for_search:index_for_search+self.dimension]
        for city in cities_data:
            idx,x,y = map(float, city.split(' '))
            self.problema[int(idx)] = (x,y)
        
        print(f'Fichero {tsp_name} parseado con exito')
        
    def read_file(self, file):
        with open(file, 'r') as reader:
            content = reader.read().splitlines()
            lines = [x.lstrip() for x in content if x != ""]
            return lines
    
    def __str__(self):
        result = f'Problema de {self.dimension} ciudades:'
        for city in self.problema.keys():
            result += f'\n{city}: {self.problema[city]}' 
        return result
    
    def solve(self):
        self.solution = list(self.problema.keys())
    
    def draw(self):
        x = [coord[0] for coord in self.problema.values()]
        y = [coord[1] for coord in self.problema.values()]
        names = list(self.problema.keys())
        plt.figure(self.figures)
        self.figures+=1
        plt.scatter(x,y,s=15, marker = 'x', c = 'black')
        for txt,x_coord, y_coord in zip(names, x, y):
            plt.annotate(txt, (x_coord, y_coord))
        plt.xlim(min(x)-1, max(x) +1)
    
    def draw_with_solution(self):
        self.draw()
        for index in range(len(self.solution)):
            x_values = self.problema[self.solution[index]][0], self.problema[self.solution[(index+1)%len(self.solution)]][0]
            y_values = self.problema[self.solution[index]][1], self.problema[self.solution[(index+1)%len(self.solution)]][1]
            print('LINEA')
            print(x_values)
            print(y_values)
            plt.plot(x_values, y_values, 'red')
            
        
        