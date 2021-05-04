# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:43:59 2021

@author: 2rome
"""

class Generador_Escenario:
    def __init__(self):
        self.dimension = 0
        self.problema = {}
        

    def obtener_desde_archivo_tsp(self, tsp_name):
        tsp_file = f"./TSP_interesantes/{tsp_name}"
        lines = self.read_file(tsp_file)
        self.dimension = int([line.partition('DIMENSION:')[2] for line in lines if 'DIMENSION: ' in line][0])
        index_for_search = [index for index,line in enumerate(lines) if 'NODE_COORD_SECTION' in line][0]  +1
        cities_data = lines[index_for_search:index_for_search+self.dimension]
        for city in cities_data:
            idx,x,y = city.split(' ')
            self.problema[idx] = (x,y)
        print(f'Fichero {tsp_name} parseado con exito')
        
    def read_file(self, file):
        with open(file, 'r') as reader:
            content = reader.read().splitlines()
            lines = [x.lstrip() for x in content if x != ""]
            return lines