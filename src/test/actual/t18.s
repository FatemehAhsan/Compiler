		.data
	.space 20
_s0:	.asciiz "salam"
	.space 20
_s1:	.asciiz "salam2"
	.space 20
_s2:	.asciiz "s"
	.space 20
_s3:	.asciiz "g"
	.space 20
_s4:	.asciiz "ss"
	.space 20
_s5:	.asciiz "df"
	.align 2
Main:	.space 44
		.text
		.globl main
main:
		la $t1, _s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t1, _s0
		la $t0, Main
		sw $t1, 28($t0)
		la $t1, _s1
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, _s2
		la $t1, _s3
		seq $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 36($t0)
		la $t0, _s0
		la $t1, _s3
		sne $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 36($t0)
		la $t0, Main
		addi $t0, $t0, 24
		lw $t0, 0($t0)
		la $t1, Main
		addi $t1, $t1, 28
		lw $t1, 0($t1)
		seq $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 36($t0)
		la $t0, Main
		addi $t0, $t0, 24
		lw $t0, 0($t0)
		la $t1, Main
		addi $t1, $t1, 28
		lw $t1, 0($t1)
		sne $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 36($t0)
		la $t0, _s4
		move $t4, $t0
_read0:
		lb $t2, 0($t0)
		beq  $t2, 0, _exit0
		addi $t0, $t0, 1
		b _read0
_exit0:
		sub $t0, $t0, $t4
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 40($t0)
		la $t0, Main
		addi $t0, $t0, 24
		lw $t0, 0($t0)
		move $t4, $t0
_read1:
		lb $t2, 0($t0)
		beq  $t2, 0, _exit1
		addi $t0, $t0, 1
		b _read1
_exit1:
		sub $t0, $t0, $t4
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 40($t0)
		la $t0, Main
		addi $t0, $t0, 24
		lw $t0, 0($t0)
		la $t1, _s5
		move $s0, $t0
_read2:
		lb $t2, 0($t0)
		beq  $t2, 0, _exit2
		addi $t0, $t0, 1
		b _read2
_exit2:
_read3:
		lb $t2, 0($t1)
		beq  $t2, 0, _exit3
		sb $t2, 0($t0)
		addi $t0, $t0, 1
		addi $t1, $t1, 1
		b _read3
_exit3:
		move $t0, $s0
		move $t4, $t0
_read4:
		lb $t2, 0($t0)
		beq  $t2, 0, _exit4
		addi $t0, $t0, 1
		b _read4
_exit4:
		sub $t0, $t0, $t4
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 40($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 20($t0)
		jr $ra
