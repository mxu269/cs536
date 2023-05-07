	.data
_a:	.word 0
	.data
_b:	.word 0
	.data
_t:	.word 0
	.data
_f:	.word 0
	.text
	.globl main
main:		# METHOD ENTRY
	sw    $ra, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0
	.data
.L0:	.asciiz "Hello world!"
	.text
	la    $t0, .L0
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 4
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	.data
.L1:	.asciiz "\n"
	.text
	la    $t0, .L1
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 4
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	li    $t0, 1675437
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 1
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	.text
	la    $t0, .L1
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 4
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	li    $t0, 0
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 1
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	.text
	la    $t0, .L1
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 4
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	li    $t0, 1
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 1
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
	.text
	la    $t0, .L1
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $v0, 4
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	syscall
_main_exit:		# FUNCTION EXIT
	lw    $ra, 0($fp)
	move  $t0, $fp		# save control link
	lw    $fp, -4($fp)	# restore FP
	move  $sp, $t0		# restore SP
	li    $v0, 10		# load exit code for syscall
	syscall		# only do this for main
