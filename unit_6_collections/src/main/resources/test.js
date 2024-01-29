var x = 10;
x = 'Hello';
x = test1()

function test1(some) {
    return some;
}

function test2() {
    return function () {
        return test2()
    }
}
