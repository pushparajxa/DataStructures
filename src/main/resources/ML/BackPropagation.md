A visual proof that neural nets can compute any function
------------------------------------------------------
    http://neuralnetworksanddeeplearning.com/chap4.html

Backpropagation example:
---------------------------
  https://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/

Perceptron, Sigmoid::
---------------------
  https://medium.com/@manoj-gupta/deep-learning-mp-neuron-perceptron-and-sigmoid-neuron-54c5a159b21d

  XOR Problem:
  ------------
    https://medium.com/@mayurchandekar99/the-xor-problem-how-a-simple-logic-puzzle-exposed-early-ais-limits-and-revolutionized-neural-60426cc7a315
    https://datahacker.rs/006-solving-the-xor-problem-using-neural-networks-with-pytorch/

Why Sigmoid function and what is the significance of its derivative?
====================================================================


    derivative of the sigmoid function equation = sig(x)(1-sig(x), where sig(x) = 1/(1+ e^-x)

    In other words, the derivative of the Sigmoid function is the Sigmoid function itself multiplied by one minus the Sigmoid function.

    The cool thing is that during backpropagation we have already calculated all the
    parts of the derivative of the Sigmoid function during the feedforward step, and
     there is therefore nothing new to calculate. Instead, we can simply apply the result of
     the Sigmoid function in the two spots of the derivative equation.

    This saves the neural network from doing a lot of computations, which is otherwise
    required when using other activation functions. Being able to avoid so many additional
    computations is one of the things that makes the Sigmoid function great for neural networks.

    Ref: https://builtin.com/articles/derivative-of-sigmoid-function#:~:text=The%20derivative%20of%20a%20function%20will%20give,of%20the%20graph%20that%20the%20function%20describes.

 Why sigmoid is not used as activation function in hidden layers?
 ----------------------------------------------------------------

   --> Vanisihing gradients,
       Sigmoid fucntion has two flat slopes, means derivative goes to zero almost, (when w is higher
        positive or negative). When derivative is goes miniscule, for hidden layers it goes more
        miniscule.

       ---> Maximum value of sigmoid function derivative is 0.25 which occurs at when x=0.



    4. The Chain Rule in Backpropagation
              During backpropagation, the gradient of the loss function (\(\mathcal{L}\)) with respect to a
              weight in an early layer is calculated by multiplying the derivatives of all
              subsequent layers and their activation functions using the chain rule:

               Check the Chainrule_Propagation_BackwardPropagation.png image in the same folder.    

    5. The Multiplication Effect

        Because each of these multiplicative terms is at most 0.25 (and
        often much smaller), repeatedly multiplying them across multiple layers causes


        Layer 9: gradient \(\propto \dots \times 0.25\)
        Layer 8: gradient \(\propto \dots \times 0.25\times 0.25=0.0625\)
        Layer 1: gradient \(\propto \dots \times (0.25)^{9}\approx 0.0000038\)
    
        his vanishingly small gradient means that the weights in the early layers receive minimal or no
        updates during training, preventing the network from learning long-range dependencies and complex patterns
        effectively


     Ref: https://www.google.com/search?q=perceptron+in+hidden+layer+is+called+as+activation%3F&rlz=1C5CHFA_enIN860IN862&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIJCAEQIRgKGKABMgkIAhAhGAoYoAEyCQgDECEYChigATIHCAQQIRiPAtIBCTEzNTIxajFqN6gCALACAA&sourceid=chrome&ie=UTF-8&udm=50&fbs=AIIjpHxU7SXXniUZfeShr2fp4giZ1Y6MJ25_tmWITc7uy4KIeiAkWG4OlBE2zyCTMjPbGmPzNVD9LjlghyJHnd1ZXeDdaT_MSrCDUyDrqMXq-jQcc6mkZlpsPPLgjW0yVxcrAKAEdMqMZcuvdFz_OEV2mrJ66cToy25AAv0FAE0rwrCil7wdtrkh-gfveQ4qDPJcffcrJT8Y2HZbee_ND5bwdN8ObmRdpg&ved=2ahUKEwjo4fe5iJyRAxWVm2oFHU41LLIQ0NsOegQIAxAB&aep=10&ntc=1&mstk=AUtExfBGNrKI3GYnw3Iguye7wAFUBAmlEU8lGM__n7Rs_NO7R5hSKPWP_vFKU3vXKAVCTMborsQIK-J33aBbvD1GPkguHmTSrM34FdQdVu9oaesz_t3gfTqVvbs2SS53BOuZfDaNsrBoeqkCTH87T82JSEMFoOqadAz_migSy060AXHO-NmKoPX10b5Dgjw1ADx_Szin5nFvd6ouPDldXTFnQKP6YGp_PlRDPpkDAMQWeQqiol3eZWII0b9QNSBZJZrbXcuO-KgeQUUfpwhNJCtuHilpaFLHj-AnmdXyLSGPHD2eQxEgQAg3McA4Wym5rEeNwVQFBbwEVRfWzg&csuir=1&mtid=E18taYekGcCyqtsP7eiPAQ


  Ref: https://www.kdnuggets.com/2022/02/vanishing-gradient-problem.html