// Way to export class if you want to use it without instantiate
class Dog {
    bark() {
        console.log("Woof woof!");
    }

    eat() {
        console.log("Eating");
    }
}

module.exports = new Dog();