		.data
	.align 2
Main:	.space 20
		.text
		.globl main
main:
		li $v0, 1
		li $a0, 77
		syscall
		li $t1, 3
		la $t0, Main
		sw $t1, 12($t0)
		li $t1, 5
		la $t0, Main
		sw $t1, 16($t0)
		li $v0, 1
		la $a0, Main
		lw $a0, 12($a0)
		syscall
		la $t0, Main
		lw $t0, 12($t0)
		la $t1, Main
		lw $t1, 16($t1)
		add $s0, $t0, $t1
		li $v0, 1
		move $a0, $s0
		syscall
		li $t1, 2
		la $t0, Main
		sw $t1, 8($t0)
		jr $ra
