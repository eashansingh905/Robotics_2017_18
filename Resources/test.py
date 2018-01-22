# -*- coding: utf-8 -*-
"""
Created on Tue Jan  9 11:22:22 2018

@author: easha
"""

#Required Imports 
import matplotlib.pyplot as plt
import tensorflow as tf
import numpy as np
from sklearn.metrics import confusion_matrix


#Attaining Data from MNIST Data Set
from tensorflow.examples.tutorials.mnist import input_data
data = input_data.read_data_sets("data/MNIST/", one_hot=True)


#print commands
print()
print("Constituents")
print("Training Set:\t\t{} ".format(len(data.train.labels)))
print("Test Set:\t\t{}".format(len(data.test.labels)))
print("Validation-set:\t{}".format(len(data.validation.labels)))


data.test.labels[0:5, :]
data.test.cls = np.array([label.argmax() for label in data.test.labels])
data.test.cls[0:5]

#size of each(pixels)
size = 28
img_size_flat = size * size
img_shape = (size, size)
# 0-9 digits; 1 class per digit 
classes = 10


# plots images on 3 by 3 grid
def plot_images(images, cls_true, cls_pred=None):
    assert len(images) == len(cls_true)== 9
    
    
    fig, axes = plt.subplots(3,3)
    fig.subplots_adjust(hspace=0.3, wspace=0.3)
    
    for i, ax in enumerate(axes.flat):
        
        ax.imshow(images[i].reshape(img_shape),cmap='binary')
        
        
        if cls_pred is None:
            xlabel = "True: {0}".format(cls_true[i])
        else: 
            xlabel ="True: {0}, Pred: {1}".format(cls_true[i],cls_pred[i])
        
        
        
        ax.set_xlabel(xlabel)
        
        ax.set_xticks([])
        ax.set_yticks([])
        
    plt.show 
        
#Retrieves and plots true         
images = data.test.images[0:9]
cls_true = data.test.cls[0:9]
plot_images(images=images, cls_true=cls_true)


#placeholders
x= tf.placeholder(tf.float32,[None,img_size_flat])
y_true= tf.placeholder(tf.float32, [None,classes])
y_true_cls = tf.placeholder(tf.int64,[None])


#Variables
weights = tf.Variable(tf.zeros([img_size_flat,classes]))
biases = tf.Variable(tf.zeros([classes]))


#Softmax Regression Model
logits = tf.matmul(x,weights) + biases

y_pred = tf.nn.softmax(logits)
y_pred_cls = tf.argmax(y_pred, axis=1)

cross_entropy = tf.nn.softmax_cross_entropy_with_logits(logits=logits, labels=y_true)


cost = tf.reduce_mean(cross_entropy)

optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.5).minimize(cost)

correct_prediction = tf.equal(y_pred_cls, y_true_cls)


accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))




session = tf.Session()


session.run(tf.global_variables_initializer())

batch_size = 500

def optimize(num_iterations):
    for i in range(num_iterations):
    
        
        x_batch, y_true_batch = data.train.next_batch(batch_size)
        
        
        feed_dict_train = {x: x_batch,
                           y_true: y_true_batch}

       
        session.run(optimizer, feed_dict=feed_dict_train)
        
feed_dict_test = {x: data.test.images,y_true: data.test.labels,  y_true_cls: data.test.cls}


def print_accuracy():
    
    acc = session.run(accuracy, feed_dict=feed_dict_test)
    
    
    print("Accuracy on test-set: {0:.1%}".format(acc))


def print_confusion_matrix():
    
    cls_true = data.test.cls
    
    #
    cls_pred = session.run(y_pred_cls, feed_dict=feed_dict_test)

   
    cm = confusion_matrix(y_true=cls_true,
                          y_pred=cls_pred)

   
    print(cm)

   
    plt.imshow(cm, interpolation='nearest', cmap=plt.cm.Blues)

    
    plt.tight_layout()
    plt.colorbar()
    tick_marks = np.arange(classes)
    plt.xticks(tick_marks, range(classes))
    plt.yticks(tick_marks, range(classes))
    plt.xlabel('Guess')
    plt.ylabel('Actual')
    
  
    plt.show()
    
    
    
def plot_example_errors():
    
    correct, cls_pred = session.run([correct_prediction, y_pred_cls],
                                    feed_dict=feed_dict_test)

   
    incorrect = (correct == False)
    
    
    images = data.test.images[incorrect]
    
   
    cls_pred = cls_pred[incorrect]

    
    cls_true = data.test.cls[incorrect]
    
 
    plot_images(images=images[0:9],
                cls_true=cls_true[0:9],
                cls_pred=cls_pred[0:9])

def plot_weights():
    
    w = session.run(weights)
    
   
    w_min = np.min(w)
    w_max = np.max(w)

   
    fig, axes = plt.subplots(3, 4)
    fig.subplots_adjust(hspace=0.3, wspace=0.3)

    for i, ax in enumerate(axes.flat):
       
        if i<10:
         
            image = w[:, i].reshape(img_shape)

           
            ax.set_xlabel("Weights: {0}".format(i))

           
            ax.imshow(image, vmin=w_min, vmax=w_max, cmap='seismic')

        
        ax.set_xticks([])
        ax.set_yticks([])
 
    plt.show()
    
    
print_accuracy()
plot_example_errors()


optimize(num_iterations=1)
print_accuracy()
plot_example_errors()
plot_weights()
optimize(num_iterations=9)
print_accuracy()
plot_example_errors()
plot_weights()
optimize(num_iterations=1000)
print_accuracy()
plot_example_errors()
plot_weights()
print_confusion_matrix()
