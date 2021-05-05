# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:43:59 2021

@author: 2rome
"""
from matplotlib import pyplot as plt
import random

class TSP:
    def __init__(self):
        self.nombre = ''
        self.dimension = 0
        self.problema = {}
        self.solution = []
        self.figures = 0
        

    def obtener_desde_archivo_tsp(self, tsp_name):
        tsp_file = f"./TSP_interesantes/{tsp_name}"
        lines = self.read_file(tsp_file)
        self.nombre = [line.partition('NAME:')[2] for line in lines if 'NAME: ' in line][0].strip()
        self.dimension = int([line.partition('DIMENSION:')[2] for line in lines if 'DIMENSION: ' in line][0])
        index_for_search = [index for index,line in enumerate(lines) if 'NODE_COORD_SECTION' in line][0]  +1
        cities_data = lines[index_for_search:index_for_search + self.dimension]
        self.problema = {}
        for city in cities_data:
            idx,x,y = map(float, city.split(' '))
            self.problema[int(idx)] = (x,y)
        
        print(f'Fichero {tsp_name} parseado con exito')
        
    def obtener_random(self, dimension):
        self.nombre = f'Aleatorio {dimension} dimensiones'
        self.dimension = dimension
        self.problema = {}
        for i in range(1, dimension+1):
            self.problema[i] =  round(random.random()*50,2), round(random.random()*50, 2)
        
            
        
        
        
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
        random.shuffle(self.solution)
    
    def draw(self):
        x = [coord[0] for coord in self.problema.values()]
        y = [coord[1] for coord in self.problema.values()]
        names = list(self.problema.keys())
        
        width = 9.6
        height = 7.2
        bool_dim = self.dimension > 20
        figsize = [width+(width*bool_dim), height+(height*bool_dim)]
        plt.figure(self.figures, figsize = figsize)
        
        self.figures+=1
        plt.scatter(x,y,s=15, marker = 'x', c = 'black')
        for txt,x_coord, y_coord in zip(names, x, y):
            plt.annotate(txt, (x_coord, y_coord))
        plt.xlim(min(x)-1, max(x) +1)
        plt.suptitle(f'{self.nombre} sin solucion', fontsize = 14)
    
    def draw_with_solution(self):
        self.draw()
        for index in range(len(self.solution)):
            x_values = self.problema[self.solution[index]][0], self.problema[self.solution[(index+1)%len(self.solution)]][0]
            y_values = self.problema[self.solution[index]][1], self.problema[self.solution[(index+1)%len(self.solution)]][1]
            print('LINEA')
            print(x_values)
            print(y_values)
            plt.plot(x_values, y_values, 'red')
        plt.suptitle(f'{self.nombre} con solucion', fontsize = 14)
        plt.title(', '.join(map(str, self.solution)), fontsize = 10)
            
        
        