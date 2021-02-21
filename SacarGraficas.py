# -*- coding: utf-8 -*-
"""
Created on Fri Feb 12 13:51:10 2021

@author: 2rome
"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

folder = "primality_files"
filename = "basic_comp"
print(f"./{folder}/{filename}.csv")
df  = pd.read_csv(f"./{folder}/{filename}.csv")

#df = sns.load_dataset("data.csv")
 
# Use the 'hue' argument to provide a factor variable
graph = sns.lmplot( x="Number", y="Time", data=df, fit_reg=False, order= 6, ci = None, hue='isPrime', legend=True, scatter_kws={"s": 10})


 
# Move the legend to an empty part of the plot
#plt.legend(loc='lower right')
graph.set(xscale="linear", yscale="linear")

graph._legend.set_title("Primalidad")
# replace labels
new_labels = ['Compuestos', 'Primos']
for t, l in zip(graph._legend.texts, new_labels): 
    t.set_text(l)

plt.title("Relación entre el número evaluado y el tiempo de ejecución")
plt.ylabel("Tiempo (ms)")
plt.xlabel("Número")
plt.ticklabel_format(style = "plain")
plt.xticks(fontsize = 8)
plt.show()
